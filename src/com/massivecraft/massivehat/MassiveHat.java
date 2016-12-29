package com.massivecraft.massivehat;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.util.InventoryUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class MassiveHat extends MassivePlugin
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static int RAW_HAT_SLOT_ID = 5;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveHat i;
	public static MassiveHat get() { return i; }
	public MassiveHat()
	{
		MassiveHat.i = this;
		
		// Version Synchronized
		this.setVersionSynchronized(true);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Activate
		this.activate(
			// Coll
			MConfColl.class,
		
			// Command
			CmdHat.class
		);
	}
	
	// -------------------------------------------- //
	// LISTENER
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void hatSwitch(InventoryClickEvent event)
	{
		// If a player ...
		if (!(event.getWhoClicked() instanceof Player)) return;
		final Player me = (Player)event.getWhoClicked();
		
		// ... is clicking around in their own/armor/crafting view ...
		if (event.getView().getType() != InventoryType.CRAFTING) return;
		
		// ... and they are clicking their hat slot ...
		if (event.getRawSlot() != RAW_HAT_SLOT_ID) return;
		
		// ... and the cursor is a hat ...
		if ( ! isHat(event.getCursor())) return;
		
		// ... and hatting is allowed ...
		if ( ! ReqIsHattingAllowed.get().apply(me)) return;
		
		// ... then perform the switch.
		doDenyingHardSwap(event);
	}
	
	/*@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void debug(InventoryClickEvent event)
	{
		InventoryUtil.debug(event);
	}*/
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	// With "hat" we mean something that is not usually equippable but should be.
	public static boolean isHat(ItemStack itemStack)
	{
		// Nothingness should never be equipped
		if (itemStack == null) return false;
		if (itemStack.getAmount() == 0) return false;
		if (itemStack.getType() == Material.AIR) return false;
		
		// Pumpkins are equippable per default
		if (itemStack.getType() == Material.PUMPKIN) return false;
		// However all other blocks are per default not equippable but should be!
		if (itemStack.getType().isBlock()) return true;
		
		// We also want to allow banners
		// For backwards compatibility below 1.8 we use the raw string rather than enum comparison.
		// Material.BANNER
		if ("BANNER".equals(itemStack.getType().name())) return true;
		
		// Everything else is not allowed.
		return false;
	}

	public static void doDenyingHardSwap(final InventoryClickEvent event)
	{
		// We deny the normal result
		// NOTE: There is no need to cancel the event since that is just a proxy method for the line below. 
		event.setResult(Result.DENY);
		
		// Schedule swap
		Bukkit.getScheduler().scheduleSyncDelayedTask(MassiveHat.get(), new Runnable()
		{
			@Override
			public void run()
			{
				final ItemStack current = event.getCurrentItem();
				final ItemStack cursor = event.getCursor();
				event.setCurrentItem(cursor);
				event.getView().setCursor(current);
				InventoryUtil.update(event.getWhoClicked());
			}
		});
	}
	
}

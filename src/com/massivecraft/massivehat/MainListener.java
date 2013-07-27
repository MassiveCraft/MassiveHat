package com.massivecraft.massivehat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.massivecraft.mcore.util.InventoryUtil;
import com.massivecraft.mcore.util.Txt;

public class MainListener implements Listener
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static int HAT_SLOT_ID = 5;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MainListener i = new MainListener();
	public static MainListener get() { return i; }
	public MainListener() {}
	
	// -------------------------------------------- //
	// SETUP
	// -------------------------------------------- //
	
	public void setup()
	{
		Bukkit.getPluginManager().registerEvents(this, MassiveHat.get());
	}
	
	// -------------------------------------------- //
	// LISTENER
	// -------------------------------------------- //
	
	// This performs the actual switch
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void hatSwitch(InventoryClickEvent event)
    {
		// If a player is clicking their hat slot ...
		final Player me = getHattingPlayer(event);
		if (me == null) return;
		
		final ItemStack current = event.getCurrentItem();
		final ItemStack cursor = event.getCursor();
		final PlayerInventory inv = me.getInventory();
		final InventoryView view = event.getView();
		
		// ... and they are holding something ...
		if (cursor.getAmount() == 0) return;
		if (cursor.getType() == Material.AIR) return;
		
		// ... and it's a block ...
		if (cursor.getType().isBlock() == false) return;
		
		// ... and it isn't a pumpkin ..
		if (cursor.getType() == Material.PUMPKIN) return;
		
		// ... check war arena ...
		if (MassiveHat.isInWarArena(me) && ! Perm.WARUSE.has(me, true)) return;
		
		// ... check whether they have permission i general...
		if (!Perm.USE.has(me, false))
		{
			for(String line : Conf.get().getMsgCant())
			{
				me.sendMessage(Txt.parse(line));
			}
			return;
		}
		
		// ... perform the switch ...
		event.setResult(Result.DENY);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(MassiveHat.get(), new Runnable()
		{
			@Override
			public void run()
			{
				inv.setHelmet(cursor);
				view.setCursor(current);
				InventoryUtil.update(me);
			}
		});
		
		// ... and report the success!
		for (String line : Conf.get().getMsgCan())
		{
			me.sendMessage(Txt.parse(line));
		}
    }
	
	public static Player getHattingPlayer(InventoryClickEvent event)
	{
		// If a player ...
		if (!(event.getWhoClicked() instanceof Player)) return null;
		
		// ... is clicking around in their own/armor/crafting view ...
		if (event.getView().getType() != InventoryType.CRAFTING) return null;
		
		// ... and they are clicking their hat slot ...
		if (event.getRawSlot() != HAT_SLOT_ID) return null;
		
		// ... then it is indeed a "hatting" player :P
		return (Player)event.getWhoClicked();
	}
}

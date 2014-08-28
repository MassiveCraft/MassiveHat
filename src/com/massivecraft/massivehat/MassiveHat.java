package com.massivecraft.massivehat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.util.InventoryUtil;
import com.tommytony.war.Team;

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
	public MassiveHat() { MassiveHat.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdHat cmdHat;
	public CmdHat getCmdHat() { return this.cmdHat; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Collections
		MConfColl.get().init();
		
		// Commands
		this.cmdHat = new CmdHat();
		this.cmdHat.register();
		
		postEnable();
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
		
		// ... and the cursor isn't a pumpkin ..
		if (event.getCursor().getType() == Material.PUMPKIN) return;
		
		// ... and the cursor is a hat ...
		if (!isHat(event.getCursor())) return;
		
		// ... and hatting is allowed ...
		if (!ReqIsHattingAllowed.get().apply(me)) return;
		
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
	
	public static boolean isHat(ItemStack itemStack)
	{
		if (itemStack == null) return false;
		if (itemStack.getAmount() == 0) return false;
		if (itemStack.getType() == Material.AIR) return false;
		if (itemStack.getType().isBlock() == false) return false;
		return true;
	}
	
	public static boolean isInWarArena(CommandSender sender)
	{
		if (sender == null) return false;
		if (!(sender instanceof Player)) return false;
		Player player = (Player)sender;
		Plugin test = Bukkit.getPluginManager().getPlugin("War");
		if (test == null || !test.isEnabled()) return false;
		Team team = Team.getTeamByPlayerName(player.getName());
		return team != null;
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

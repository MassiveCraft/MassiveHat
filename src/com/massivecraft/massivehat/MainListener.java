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
import org.bukkit.inventory.ItemStack;

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
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void hatSwitch(InventoryClickEvent event)
    {
		// If a player is clicking their hat slot ...
		final Player me = getHattingPlayer(event);
		if (me == null) return;
		
		// ... and they are holding something ...
		if (event.getCursor().getAmount() == 0) return;
		if (event.getCursor().getType() == Material.AIR) return;
		
		// ... and it's a block ...
		if (event.getCursor().getType().isBlock() == false) return;
		
		// ... and it isn't a pumpkin ..
		if (event.getCursor().getType() == Material.PUMPKIN) return;
		
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
		
		// ... if they do we switch the slot contents ...
		ItemStack current = event.getCurrentItem();
		ItemStack cursor = event.getCursor();
		
		event.setCurrentItem(cursor);
		event.setCursor(current);
		
		event.setResult(Result.ALLOW);
		
		// ... and report the success!
		for(String line : Conf.get().getMsgCan())
		{
			me.sendMessage(Txt.parse(line));
		}
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
    public void hatUpdate(InventoryClickEvent event)
    {
		// If a player is clicking their hat slot ...
		final Player player = getHattingPlayer(event);
		if (player == null) return;
		
		// ... force an update update to avoid odd client bugs. 
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(MassiveHat.get(), new Runnable(){
			@Override
			public void run()
			{
				player.updateInventory();
			}
		});
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

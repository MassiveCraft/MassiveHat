package com.massivecraft.massivehat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.massivecraft.mcore.MPlugin;
import com.tommytony.war.Team;

public class MassiveHat extends MPlugin
{
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
	private OldCmd oldCmd;
	public OldCmd getOldCmd() { return this.oldCmd; }
	
	// -------------------------------------------- //
	// OVERRIED
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;

		// Load Server Config
		ConfServer.get().load();
		
		// Initialize Collections
		ConfColl.get().init();
		
		// Setup Listeners
		MainListener.get().setup();
		
		// Commands
		this.oldCmd = new OldCmd();
		if (this.oldCmd.getAliases() != null && this.oldCmd.getAliases().size() > 0)
		{
			this.oldCmd.register(this, true);
		}
		
		postEnable();
	}
	
	// -------------------------------------------- //
	// UTIL
	// -------------------------------------------- //
	
	public static boolean isInWarArena(Player player)
	{
		Plugin test = Bukkit.getPluginManager().getPlugin("War");
		if (test == null || !test.isEnabled()) return false;
		Team team = Team.getTeamByPlayerName(player.getName());
		return team != null;
	}
	
}
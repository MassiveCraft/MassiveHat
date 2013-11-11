package com.massivecraft.massivehat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.req.ReqAbstract;
import com.massivecraft.mcore.util.PermUtil;
import com.massivecraft.mcore.util.Txt;

public class ReqIsHattingAllowed extends ReqAbstract
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ReqIsHattingAllowed i = new ReqIsHattingAllowed();
	public static ReqIsHattingAllowed get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender, MCommand command)
	{
		return this.createErrorMessage(sender, command) == null;
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MCommand command)
	{
		if (!(sender instanceof Player))
		{
			return Txt.parse("<b>Only players can equip hats.");
		}
		
		if (MassiveHat.isInWarArena(sender) && !sender.hasPermission(Perm.WARUSE.node))
		{
			return PermUtil.getDeniedMessage(Perm.WARUSE.node);
		}
		
		if (!sender.hasPermission(Perm.USE.node))
		{
			return PermUtil.getDeniedMessage(Perm.USE.node);
		}
		
		return null;
	}
	
}

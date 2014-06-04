package com.massivecraft.massivehat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.req.ReqAbstract;
import com.massivecraft.massivecore.util.PermUtil;
import com.massivecraft.massivecore.util.Txt;

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
	public boolean apply(CommandSender sender, MassiveCommand command)
	{
		return this.createErrorMessage(sender, command) == null;
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
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

package com.massivecraft.massivehat;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementAbstract;
import com.massivecraft.massivecore.util.PermissionUtil;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReqIsHattingAllowed extends RequirementAbstract
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
		
		if (!sender.hasPermission(Perm.USE.getId()))
		{
			return PermissionUtil.getPermissionDeniedMessage(Perm.USE.getId());
		}
		
		return null;
	}
	
}

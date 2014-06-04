package com.massivecraft.massivehat;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdHat extends MassiveCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdHat()
	{
		// Requirements
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqIsHattingAllowed.get());
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesHat;
	}
	
	@Override
	public void perform()
	{
		EntityEquipment equipment = me.getEquipment();
		final ItemStack inhand = equipment.getItemInHand();
		final ItemStack helmet = equipment.getHelmet();
		
		if (!MassiveHat.isHat(inhand))
		{
			msg("<b>You are not holding a hat in your hand.");
			return;
		}
		
		equipment.setHelmet(inhand);
		equipment.setItemInHand(helmet);
		
		// This command is especially useful in creative mode since the normal inventory equip hack cannot be implemented there.
		// The client does not the relevant packets to the server in creative mode inventory.
		if (me.getGameMode() != GameMode.CREATIVE)
		{
			msg("<h>NOTE: <i>You can equip in your inventory <h>like a regular helmet<i>.");
		}
		
	}
	
}

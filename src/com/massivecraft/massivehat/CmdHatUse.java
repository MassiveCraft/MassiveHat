package com.massivecraft.massivehat;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.util.InventoryUtil;
import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CmdHatUse extends MassiveCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdHatUse i = new CmdHatUse() { @Override public List<String> getAliases() { return MConf.get().aliasesHatUseOutter; }};
	public static CmdHatUse get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdHatUse()
	{
		// Requirements
		this.addRequirements(RequirementIsPlayer.get());
		this.addRequirements(RequirementHasPerm.get(Perm.USE));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		final ItemStack inhand = InventoryUtil.getWeapon(me);
		final ItemStack helmet = InventoryUtil.getHelmet(me);
		
		if ( ! MassiveHat.isHat(inhand))
		{
			msg("<b>You are not holding a hat in your hand.");
			return;
		}
		
		InventoryUtil.setHelmet(me, inhand);
		InventoryUtil.setWeapon(me, helmet);
		
		// This command is especially useful in creative mode since the normal inventory equip hack cannot be implemented there.
		// The client does not the relevant packets to the server in creative mode inventory.
		if (me.getGameMode() == GameMode.CREATIVE) return;
		msg("<h>NOTE: <i>You can equip in your inventory <h>like a regular helmet<i>.");
	}
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesHatUseInner;
	}
	
}

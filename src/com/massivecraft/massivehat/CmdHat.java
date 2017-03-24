package com.massivecraft.massivehat;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.MassiveCommandVersion;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdHat extends MassiveCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdHat i = new CmdHat();
	public static CmdHat get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdHatUse cmdHatUse = new CmdHatUse();
	public CmdHatConfig cmdHatConfig = new CmdHatConfig();
	public MassiveCommandVersion cmdVersion = new MassiveCommandVersion(MassiveHat.get()).addRequirements(RequirementHasPerm.get(Perm.VERSION));
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdHat()
	{
		// Children
		this.addChild(this.cmdHatUse);
		this.addChild(this.cmdHatConfig);
		this.addChild(this.cmdVersion);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.BASECOMMAND));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesHat;
	}
	
}

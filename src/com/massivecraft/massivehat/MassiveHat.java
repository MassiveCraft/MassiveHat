package com.massivecraft.massivehat;

import com.massivecraft.massivecore.MassivePlugin;

public class MassiveHat extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveHat i;
	public static MassiveHat get() { return i; }
	public MassiveHat() { MassiveHat.i = this; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Activate
		this.activateAuto();
	}
	
}

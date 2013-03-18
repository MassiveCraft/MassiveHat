package com.massivecraft.massivehat;

import java.util.List;

import com.massivecraft.mcore.SimpleConfig;
import com.massivecraft.mcore.util.MUtil;

public class ConfServer extends SimpleConfig
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static transient ConfServer i = new ConfServer();
	public static ConfServer get() { return i; }
	private ConfServer() { super(MassiveHat.get()); }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public static String dburi = "default";
	public static List<String> aliasesOld = MUtil.list("hat", "blockhat", "massivehat", "mhat");
	
}

package com.massivecraft.massivehat;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

@EditorName("config")
public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public List<String> aliasesHat = MUtil.list("massivehat");

	public List<String> aliasesHatUseInner = MUtil.list("use", "wear", "apply");
	public List<String> aliasesHatUseOutter = MUtil.list("usehat", "hat", "blockhat", "mhat");

	public List<String> aliasesConfigEditInner = MUtil.list("config");
	
}

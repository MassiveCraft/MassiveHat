package com.massivecraft.massivehat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Entity;
import com.massivecraft.mcore.util.MUtil;

public class Conf extends Entity<Conf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static Conf get()
	{
		return ConfColl.get().get(MCore.INSTANCE);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Conf load(Conf that)
	{
		this.msgCan = that.msgCan;
		this.msgCant = that.msgCant;
		this.msgCmdCan = that.msgCmdCan;
		this.msgCmdCant = that.msgCmdCant;
		
		return this;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private List<String> msgCan = new ArrayList<String>();
	public List<String> getMsgCan() { return msgCan == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCan); }
	public void setMsgCan(Collection<String> msgCan) { this.msgCan = msgCan == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCan); this.changed(); }
	
	private List<String> msgCant = MUtil.list("&cYou don't have premission to wear blocks as a hat.");
	public List<String> getMsgCant() { return msgCant == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCant); }
	public void setMsgCant(Collection<String> msgCant) { this.msgCant = msgCant == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCant); this.changed(); }
	
	private List<String> msgCmdCan = MUtil.list("&aJust equip the block in your inventory &dlike a regular helmet&a.");
	public List<String> getMsgCmdCan() { return msgCmdCan == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCmdCan); }
	public void setMsgCmdCan(Collection<String> msgCmdCan) { this.msgCmdCan = msgCmdCan == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCmdCan); this.changed(); }
	
	private List<String> msgCmdCant = MUtil.list("&cYou don't have premission to wear blocks as a hat.");
	public List<String> getMsgCmdCant() { return msgCmdCant == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCmdCant); }
	public void setMsgCmdCant(Collection<String> msgCmdCant) { this.msgCmdCant = msgCmdCant == null ? new ArrayList<String>() : new ArrayList<String>(this.msgCmdCant); this.changed(); }

}

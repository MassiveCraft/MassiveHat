package com.massivecraft.massivehat;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

public class ConfColl extends Coll<Conf, String>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ConfColl i = new ConfColl();
	public static ConfColl get() { return i; }
	private ConfColl()
	{
		super(MStore.getDb(ConfServer.dburi), MassiveHat.get(), "ai", Const.COLLECTION_BASENAME_CONF, Conf.class, String.class, true);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void init()
	{
		super.init();
		this.get(MCore.INSTANCE);
	}
	
}

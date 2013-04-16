package com.massivecraft.massivehat;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

public class ConfColl extends Coll<Conf>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ConfColl i = new ConfColl();
	public static ConfColl get() { return i; }
	private ConfColl()
	{
		super(Const.COLLECTION_BASENAME_CONF, Conf.class, MStore.getDb(ConfServer.dburi), MassiveHat.get(), true, false);
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

package com.massivecraft.massivehat;

import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class OldCmd extends MCommand
{	
	public OldCmd()
	{
		super();
		this.setAliases(ConfServer.aliasesOld);
		this.setErrorOnToManyArgs(false);
		this.addRequirements(ReqIsPlayer.get());
	}

	@Override
	public void perform()
	{
		if (Perm.USE.has(me, false))
		{
			msg(Conf.get().getMsgCmdCan());
		}
		else
		{
			msg(Conf.get().getMsgCmdCant());
		}
	}
}

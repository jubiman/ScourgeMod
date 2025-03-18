package com.jubiman.scourgemod.buff;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public abstract class ScourgePassiveBuff extends Buff {
	public ScourgePassiveBuff() {
		this.canCancel = false;
		this.isVisible = false;
		this.isPassive = true;
		this.shouldSave = true;
		this.overrideSync = true;
	}

	@Override
	public void init(ActiveBuff activeBuff, BuffEventSubscriber buffEventSubscriber) {
//		tick(activeBuff);
	}

	@Override
	public void serverTick(ActiveBuff buff) {
		if (buff.owner.isPlayer) {
			PlayerMob owner = (PlayerMob) buff.owner;
			buffs(buff, ScourgePlayersHandler.getPlayer(owner.getServerClient().authentication));
		}
	}

	@Override
	public void clientTick(ActiveBuff buff) {
		// TODO: is this needed?
	}

	protected void tick(ActiveBuff buff) {
		if (buff.owner.isPlayer) {
			PlayerMob owner = (PlayerMob) buff.owner;
			if (owner.isClientClient())
				buffs(buff, ScourgePlayersHandler.getPlayer(owner.getClientClient().authentication));
			else if (owner.isServerClient())
				buffs(buff, ScourgePlayersHandler.getPlayer(owner.getServerClient().authentication));
		}
	}

	protected abstract void buffs(ActiveBuff buff, ScourgePlayer player);
}

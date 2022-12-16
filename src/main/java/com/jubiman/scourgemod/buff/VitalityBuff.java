package com.jubiman.scourgemod.buff;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayers;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

public class VitalityBuff extends Buff {
	public VitalityBuff() {
		this.canCancel = false;
		this.isVisible = true; // TODO: debug
		this.isPassive = true;
		this.shouldSave = true;
	}

	@Override
	public void init(ActiveBuff activeBuff) {
		tick(activeBuff);
	}

	@Override
	public void serverTick(ActiveBuff buff) {
		tick(buff);
	}

	@Override
	public void clientTick(ActiveBuff buff) {
		tick(buff);
	}

	private void tick(ActiveBuff buff) {
		if (buff.owner.isPlayer) {
			PlayerMob owner = (PlayerMob) buff.owner;
			if (owner.isClientClient())
				buffs(buff, ScourgePlayers.getPlayer(owner.getClientClient().authentication));
			else if (owner.isServerClient() && owner.getServerClient().getServer().tickManager().isFirstGameTickInSecond())
				buffs(buff, ScourgePlayers.getPlayer(owner.getServerClient().authentication));
		}
	}
	private static void buffs(ActiveBuff buff, ScourgePlayer player) {
		if (buff.getGndData().getInt("lastKnownVit") != player.getVitality() || ScourgePlayers.forceBuffTick) {
			buff.setModifier(BuffModifiers.MAX_HEALTH, player.getVitalityHPBoost());
			buff.setModifier(BuffModifiers.ARMOR, player.getVitalityArmorBoost());
			buff.getGndData().setInt("lastKnownVit", player.getVitality());
			ScourgePlayers.forceBuffTick = false;
		}
	}
}

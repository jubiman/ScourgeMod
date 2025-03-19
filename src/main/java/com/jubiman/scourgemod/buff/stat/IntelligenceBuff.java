package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Intelligence;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class IntelligenceBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		buffs(buff, player.getIntelligenceObject());
	}

	@Override
	protected void buffs(ActiveBuff buff, ScourgeClient player) {
		buffs(buff, player.intelligence);
	}

	private void buffs(ActiveBuff buff, Intelligence intelligence) {
		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, intelligence.getIntelligenceMDBoost());
		buff.setModifier(BuffModifiers.MAGIC_ATTACK_SPEED, intelligence.getIntelligenceMASBoost());
		buff.setModifier(BuffModifiers.MAX_SUMMONS, intelligence.getIntelligenceMSBoost());
		buff.setModifier(BuffModifiers.MAX_MANA, intelligence.getIntelligenceMMBoost());
	}
}

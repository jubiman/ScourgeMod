package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Intelligence;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class IntelligenceBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		Intelligence intelligence = player.getIntelligenceObject();
		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, intelligence.getIntelligenceMDBoost());
		buff.setModifier(BuffModifiers.MAGIC_ATTACK_SPEED, intelligence.getIntelligenceMASBoost());
		buff.setModifier(BuffModifiers.MAX_SUMMONS, intelligence.getIntelligenceMSBoost());
		buff.setModifier(BuffModifiers.MAX_MANA, intelligence.getIntelligenceMMBoost());
	}
}

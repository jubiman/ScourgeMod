package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Strength;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class StrengthBuff extends StatBuff {
	@Override
	public void buffs(ActiveBuff buff, ScourgePlayer player) {
		buffs(buff, player.getStrengthObject());
	}

	@Override
	protected void buffs(ActiveBuff buff, ScourgeClient player) {
		buffs(buff, player.strength);
	}

	private void buffs(ActiveBuff buff, Strength strength) {
		buff.setModifier(BuffModifiers.ARMOR_PEN_FLAT, strength.getStrengthAPBoost());
		buff.setModifier(BuffModifiers.MELEE_DAMAGE, strength.getStrengthDMGBoost());
		buff.setModifier(BuffModifiers.RANGED_DAMAGE, strength.getStrengthRDBoost());
		buff.setModifier(BuffModifiers.KNOCKBACK_INCOMING_MOD, strength.getStrengthKBRBoost());
	}


}

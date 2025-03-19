package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Dexterity;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class DexterityBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		buffs(buff, player.getDexterityObject());
	}

	@Override
	protected void buffs(ActiveBuff buff, ScourgeClient player) {
		buffs(buff, player.dexterity);
	}

	private void buffs(ActiveBuff buff, Dexterity dexterity) {
		buff.setModifier(BuffModifiers.SPEED, dexterity.getDexterityMSBoost());
		buff.setModifier(BuffModifiers.ATTACK_MOVEMENT_MOD, dexterity.getDexterityAMSBoost());
		buff.setModifier(BuffModifiers.STAMINA_CAPACITY, dexterity.getDexteritySCBoost());
		buff.setModifier(BuffModifiers.CRIT_CHANCE, dexterity.getDexterityCCBoost());
		buff.setModifier(BuffModifiers.RANGED_DAMAGE, dexterity.getDexterityRDBoost());
	}
}

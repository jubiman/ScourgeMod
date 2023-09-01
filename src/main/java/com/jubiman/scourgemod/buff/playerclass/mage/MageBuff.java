package com.jubiman.scourgemod.buff.playerclass.mage;

import com.jubiman.scourgemod.buff.ScourgePassiveBuff;
import com.jubiman.scourgemod.modifier.ScourgeBuffModifiers;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.mage.MageClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class MageBuff extends ScourgePassiveBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		MageClass mageClass = (MageClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, mageClass.getDamageBuff());
		buff.setModifier(BuffModifiers.MAGIC_ATTACK_SPEED, mageClass.getAttackSpeedBuff());

		buff.setModifier(BuffModifiers.MANA_REGEN, mageClass.getManaRegenBuff());
		buff.setModifier(BuffModifiers.MANA_REGEN_FLAT, 20f);
		buff.setModifier(BuffModifiers.COMBAT_MANA_REGEN, mageClass.getManaRegenBuff());
		buff.setModifier(BuffModifiers.COMBAT_MANA_REGEN_FLAT, 20f);
		buff.setModifier(BuffModifiers.MAX_MANA, mageClass.getMaxManaBuff());

		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, mageClass.getDamageBuff());
		buff.setModifier(BuffModifiers.SUMMONS_SPEED, mageClass.getSummonsSpeedBuff());
		buff.setModifier(BuffModifiers.SUMMON_ATTACK_SPEED, mageClass.getAttackSpeedBuff());
		buff.setModifier(BuffModifiers.SUMMONS_TARGET_RANGE, mageClass.getSummonsTargetRangeBuff());

		buff.setModifier(BuffModifiers.RANGED_DAMAGE, mageClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, mageClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.MELEE_DAMAGE, mageClass.getOtherDamageTypesDebuff());
	}
}

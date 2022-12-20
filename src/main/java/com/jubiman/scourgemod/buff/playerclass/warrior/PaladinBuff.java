package com.jubiman.scourgemod.buff.playerclass.warrior;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.warrior.PaladinClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class PaladinBuff extends WarriorBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		PaladinClass paladinClass = (PaladinClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.ARMOR, paladinClass.getArmorPercentBuff());
		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, paladinClass.getDamageBuff());
	}
}

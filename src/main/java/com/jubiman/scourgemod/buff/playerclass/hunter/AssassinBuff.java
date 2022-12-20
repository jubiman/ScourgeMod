package com.jubiman.scourgemod.buff.playerclass.hunter;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.hunter.AssassinClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class AssassinBuff extends HunterBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		AssassinClass assassinClass = ((AssassinClass) player.getPlayerClass());

		buff.setModifier(BuffModifiers.MELEE_DAMAGE, assassinClass.getDamageBuff()); // TODO: create dagger modifier
	}
}

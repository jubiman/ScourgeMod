package com.jubiman.scourgemod.player.playerclass.warrior;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class PaladinClass extends WarriorClass {
	public PaladinClass(ScourgePlayer player) {
		super("scourge_paladin", "paladin", player, "warrior.PaladinClass");
	}

	public float getArmorPercentBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}


	@Override
	public float getDamageBuff() {
		return super.getDamageBuff() * 1.3f;
	}

	@Override
	public float getAttackSpeedBuff() {
		return super.getAttackSpeedBuff() * 1.3f;
	}

	@Override
	public int getArmorBuff() {
		return super.getArmorBuff() * 5;
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -.3f;
	}
}

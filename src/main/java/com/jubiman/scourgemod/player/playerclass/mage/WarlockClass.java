package com.jubiman.scourgemod.player.playerclass.mage;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class WarlockClass extends MageClass {
	public WarlockClass(ScourgePlayer player) {
		super("scourge_warlock", "warlock", player, "mage.WarlockClass");
	}

	@Override
	public float getMaxManaBuff() {
		return super.getMaxManaBuff() * 0.77f;
	}

	@Override
	public float getDamageBuff() {
		return super.getDamageBuff() * 10;
	}

	@Override
	public float getSummonsTargetRangeBuff() {
		return 0;
	}

	@Override
	public float getSummonsSpeedBuff() {
		return 0;
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.3f;
	}
}

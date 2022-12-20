package com.jubiman.scourgemod.player.playerclass.tank;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class GuardianClass extends TankClass {
	public GuardianClass(ScourgePlayer player) {
		super("scourge_guardian", "guardian", player, "tank.GuardianClass");
	}

	@Override
	public float getArmorBuff() {
		return super.getArmorBuff() * 5;
	}

	@Override
	public float getKBIncBuff() {
		return super.getKBIncBuff() * 3;
	}

	@Override
	public float getMaxHealthBuff() {
		return super.getMaxHealthBuff() * 7;
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.9f;
	}
}

package com.jubiman.scourgemod.player.playerclass.hunter;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class RangerClass extends HunterClass {
	public RangerClass(ScourgePlayer player) {
		super("scourge_ranger", "ranger", player, "hunter.RangerClass");
	}

	public float getRangedDamageBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}

	public float getProjectileSpeedBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}

	@Override
	public float getAttackSpeedBuff() {
		return super.getAttackSpeedBuff() * 10;
	}

	@Override
	public float getCritDamageBuff() {
		return super.getCritDamageBuff() * 10;
	}

	@Override
	public float getArmorPenBuff() {
		return super.getArmorPenBuff() * 3;
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.3f; // TODO: maybe reverse scale off player level?
	}
}

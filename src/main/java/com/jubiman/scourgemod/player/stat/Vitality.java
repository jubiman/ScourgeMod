package com.jubiman.scourgemod.player.stat;

public class Vitality extends Stat {
	public Vitality() {
		super();
	}

	public Vitality(int level) {
		super(level);
	}

	@Override
	public StatType getStatType() {
		return StatType.VITALITY;
	}

	public float getVitalityHPBoost() {
		return (float) (0.01 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getVitalityArmorBoost() {
		return (float) (0.005 * getTotalLevel() * (getTotalLevel() - 1));
	}
}

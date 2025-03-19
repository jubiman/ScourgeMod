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
		return (float) (0.1 * (getTotalLevel() - 1));
	}

	public int getVitalityArmorBoost() {
		return 5 * (getTotalLevel() - 1);
	}
}

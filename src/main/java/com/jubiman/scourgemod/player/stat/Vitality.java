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

	public int getVitalityHPBoost() {
		return 10 * (getTotalLevel() - 1);
	}

	public int getVitalityArmorBoost() {
		return 2 * (getTotalLevel() - 1);
	}

	public float getVitalityHRBoost() {
		return (float) (0.01 * (getTotalLevel() - 1));
	}
}

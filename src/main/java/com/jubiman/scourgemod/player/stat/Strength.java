package com.jubiman.scourgemod.player.stat;


public class Strength extends Stat {
	public Strength() {
		super();
	}

	public Strength(int level) {
		super(level);
	}

	@Override
	public StatType getStatType() {
		return StatType.STRENGTH;
	}

	public int getStrengthAPBoost() {
		return (int) (0.01 * (getTotalLevel() - 1));
	}

	public float getStrengthDMGBoost() {
		return 0.01f * (getTotalLevel() - 1);
	}

	public float getStrengthKBRBoost() {
		return 1 - (0.001f * (getTotalLevel() - 1));
	}
}

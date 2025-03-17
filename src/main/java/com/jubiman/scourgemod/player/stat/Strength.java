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
		return (int) (0.1 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getStrengthDMGBoost() {
		return (float) (0.001 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getStrengthKBRBoost() {
		return (float) -(0.001 * getTotalLevel() * (getTotalLevel() - 1));
	}
}

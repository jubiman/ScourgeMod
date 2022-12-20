package com.jubiman.scourgemod.player.stat;

public class Strength extends Stat {
	public Strength() {
		super();
	}

	public Strength(int level) {
		super(level);
	}

	public int getStrengthAPBoost() {
		return (int) (0.1 * level * (level - 1));
	}

	public float getStrengthDMGBoost() {
		return (float) (0.001 * level * (level - 1));
	}

	public float getStrengthKBRBoost() {
		return (float) -(0.001 * level * (level - 1));
	}
}

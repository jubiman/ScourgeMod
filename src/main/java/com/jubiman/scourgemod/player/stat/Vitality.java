package com.jubiman.scourgemod.player.stat;

public class Vitality extends Stat {
	public Vitality() {
		super();
	}

	public Vitality(int level) {
		super(level);
	}

	public float getVitalityHPBoost() {
		return (float) (0.01 * level * (level - 1));
	}

	public float getVitalityArmorBoost() {
		return (float) (0.005 * level * (level - 1));
	}
}

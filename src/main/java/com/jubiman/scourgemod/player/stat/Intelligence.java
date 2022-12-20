package com.jubiman.scourgemod.player.stat;

public class Intelligence extends Stat {
	public Intelligence() {
		super();
	}

	public Intelligence(int level) {
		super(level);
	}

	public float getIntelligenceMDBoost() {
		return (float) (0.007 * level * (level - 1));
	}

	public float getIntelligenceMASBoost() {
		return (float) (0.002 * level * (level - 1));
	}

	public int getIntelligenceMSBoost() {
		return (int) (0.2 * level); // +1 max summon per 5 lvls
	}
}

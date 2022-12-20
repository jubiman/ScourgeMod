package com.jubiman.scourgemod.player.stat;

public class Dexterity extends Stat {
	public Dexterity() {
		super();
	}

	public Dexterity(int level) {
		super(level);
	}

	public float getDexterityMSBoost() {
		return (float) Math.min(Math.max(0, (0.01 * level * (level - 1))), 1.5f);
	}

	public float getDexterityAMSBoost() {
		return (float) (1 - (0.005 * level * (level - 1)));
	}

	public float getDexteritySCBoost() {
		return  (float) (0.001 * level * (level - 1));
	}

	public float getDexterityCCBoost() {
		return (float) (0.005 * level * (level - 1));
	}

	public float getDexterityRDBoost() {
		return (float) (0.001 * level * (level - 1));
	}
}

package com.jubiman.scourgemod.player.stat;


public class Dexterity extends Stat {
	public Dexterity() {
		super();
	}

	public Dexterity(int level) {
		super(level);
	}

	@Override
	public StatType getStatType() {
		return StatType.DEXTERITY;
	}

	public float getDexterityMSBoost() {
		return (float) Math.min(Math.max(0, (0.01 * getTotalLevel() * (getTotalLevel() - 1))), 1.5f);
	}

	public float getDexterityAMSBoost() {
		return (float) (1 - (0.005 * getTotalLevel() * (getTotalLevel() - 1)));
	}

	public float getDexteritySCBoost() {
		return  (float) (0.001 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getDexterityCCBoost() {
		return (float) (0.005 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getDexterityRDBoost() {
		return (float) (0.003 * getTotalLevel() * (getTotalLevel() - 1));
	}
}

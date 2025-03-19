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
		return 0.005f * (getTotalLevel() - 1);
	}

	public float getDexterityAMSBoost() {
		return 1 - (0.001f * (getTotalLevel() - 1));
	}

	public float getDexteritySCBoost() {
		return 0.005f * (getTotalLevel() - 1);
	}

	public float getDexterityCCBoost() {
		return 0.02f * (getTotalLevel() - 1);
	}

	public float getDexterityCDBoost() {
		return 0.005f * (getTotalLevel() - 1);
	}

	public float getDexterityRDBoost() {
		return 0.01f * (getTotalLevel() - 1);
	}

	public float getDexterityASBoost() {
		return 0.005f * (getTotalLevel() - 1);
	}
}

package com.jubiman.scourgemod.player.stat;

import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;

public class Dexterity extends Stat {
	public Dexterity() {
		super();
	}

	public Dexterity(int level) {
		super(level);
	}

	@Override
	public void addGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost += gemstoneQuality.dexterity;
	}

	@Override
	public void removeGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost -= gemstoneQuality.dexterity;
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
		return (float) (0.001 * getTotalLevel() * (getTotalLevel() - 1));
	}
}

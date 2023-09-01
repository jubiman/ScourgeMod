package com.jubiman.scourgemod.player.stat;

import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;

public abstract class Stat {
	protected static final int defaultLevel = 1; // TODO: make config for no fucking reason?
	protected int level;
	protected int boost;

	public Stat() {
		this(defaultLevel);
	}

	public Stat(int level) {
		this.level = level;
	}

	public int getBoost() {
		return boost;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void addLevel(int toAdd) {
		this.level += toAdd;
	}

	public void reset() {
		this.level = defaultLevel;
	}

	public abstract void addGemstoneBoost(GemstoneQuality gemstoneQuality);

	public abstract void removeGemstoneBoost(GemstoneQuality gemstoneQuality);

	public void resetBoost() {
		this.boost = 0;
	}

	public int getTotalLevel() {
		return this.level + this.boost;
	}
}

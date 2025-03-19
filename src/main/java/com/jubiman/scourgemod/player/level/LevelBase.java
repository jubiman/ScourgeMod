package com.jubiman.scourgemod.player.level;

import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

public class LevelBase {
	private long exp;
	private long threshold;

	public LevelBase() {
		exp = 0;
		threshold = nextThreshold();
	}

	public boolean addExp(long amount) {
		exp += amount;
		return levelUp();
	}

	public boolean removeExp(long amount){
		int oldLevel = getLevel();
		exp -= amount;
		if(exp < 0)
			exp = 0;
		if (getLevel() < oldLevel){
			threshold = nextThreshold();
			return true;
		}
		return false;
	}

	public boolean setExp(long amount) {
		exp = amount;
		return levelUp();
	}

	public boolean levelUp() {
		if(exp >= threshold) {
			threshold = nextThreshold();
			return true;
		}
		return false;
		// TODO: unlock items/rewards and add messages?
	}

	public int getLevel() {
		return (int) Math.floor(Math.pow(exp / 50d, 1/2.3));
	}

	public long getExp() {
		return exp;
	}

	private long nextThreshold() {
		return nextThreshold(getLevel());
	}

	public long nextThreshold(int level) {
		// TODO: decide whether to use this formula or a different one
		// http://howtomakeanrpg.com/a/how-to-make-an-rpg-levels.html
		return Math.round(50 * Math.pow(level + 1, 2.3));
	}

	public long previousThreshold() {
		return nextThreshold(getLevel() - 1);
	}

	public void save(String name, SaveData data) {
		SaveData levelbase = new SaveData(name);
		levelbase.addLong("exp", exp);
		levelbase.addLong("threshold", threshold);
		data.addSaveData(levelbase);
	}

	public void load(LoadData data) {
		exp = data.getLong("exp");
		threshold = data.getLong("threshold");
	}

	public long getThreshold() {
		return threshold;
	}

	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}
}

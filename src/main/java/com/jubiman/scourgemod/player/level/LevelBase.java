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
		exp -= amount;
		return levelUp();
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
		// TODO: find out how to get level from exp
		return (int) Math.floor((250 + Math.sqrt(62500 + 1000 * exp)) / 500);
	}

	public long getExp() {
		return exp;
	}

	private long nextThreshold() {
		// TODO: decide whether to use this formula or a different one
		// http://howtomakeanrpg.com/a/how-to-make-an-rpg-levels.html
		int x = getLevel() + 1;
		return 250L * ((long) x * x) - (250L * x);
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
}

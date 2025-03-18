package com.jubiman.scourgemod.player;

import com.jubiman.customdatalib.client.CustomClient;
import com.jubiman.scourgemod.player.level.LevelBase;
import com.jubiman.scourgemod.player.stat.*;
import necesse.engine.network.client.Client;
import org.jetbrains.annotations.NotNull;

public class ScourgeClient extends CustomClient {
	public final LevelBase level;
	public final Vitality vitality;
	public final Strength strength;
	public final Dexterity dexterity;
	public final Intelligence intelligence;
	public final Charisma charisma;
	private int statPoints;

	public ScourgeClient(Client client) {
		super(client);

		this.level = new LevelBase();
		this.vitality = new Vitality();
		this.strength = new Strength();
		this.dexterity = new Dexterity();
		this.intelligence = new Intelligence();
		this.charisma = new Charisma();

		this.statPoints = 0;
	}


	public void setStatLevel(Stat.StatType stat, int level) {
		switch (stat) {
			case VITALITY:
				vitality.setLevel(level);
				break;
			case STRENGTH:
				strength.setLevel(level);
				break;
			case DEXTERITY:
				dexterity.setLevel(level);
				break;
			case INTELLIGENCE:
				intelligence.setLevel(level);
				break;
			case CHARISMA:
				charisma.setLevel(level);
				break;
		}
	}

	public int getStatPoints() {
		return statPoints;
	}

	public void setStatPoints(int statPoints) {
		this.statPoints = statPoints;
	}

	public void levelStat(Stat.StatType statType, int amount) {
		switch (statType) {
			case VITALITY:
				vitality.addLevel(amount);
				break;
			case STRENGTH:
				strength.addLevel(amount);
				break;
			case DEXTERITY:
				dexterity.addLevel(amount);
				break;
			case INTELLIGENCE:
				intelligence.addLevel(amount);
				break;
			case CHARISMA:
				charisma.addLevel(amount);
				break;
		}
		statPoints -= amount;
	}

	/**
	 * Remove stat points from a stat
	 * @param statType The stat to remove points from
	 * @param amount The amount of points to remove where amount > 0
	 */
	public void removeStat(Stat.StatType statType, int amount) {
		switch (statType) {
			case VITALITY:
				vitality.addLevel(-amount);
				break;
			case STRENGTH:
				strength.addLevel(-amount);
				break;
			case DEXTERITY:
				dexterity.addLevel(-amount);
				break;
			case INTELLIGENCE:
				intelligence.addLevel(-amount);
				break;
			case CHARISMA:
				charisma.addLevel(-amount);
				break;
		}
		statPoints += amount;
	}

	public Stat getStat(Stat.StatType statType) {
		switch (statType) {
			case VITALITY:
				return vitality;
			case STRENGTH:
				return strength;
			case DEXTERITY:
				return dexterity;
			case INTELLIGENCE:
				return intelligence;
			case CHARISMA:
				return charisma;
		}
		return null;
	}

	public int getStatFromBuffID(@NotNull String stringID) {
		switch (stringID) {
			case "scourge_vitality": {
				return vitality.getLevel();
			}
			case "scourge_dexterity": {
				return dexterity.getTotalLevel();
			}
			case "scourge_intelligence": {
				return intelligence.getTotalLevel();
			}
			case "scourge_strength": {
				return strength.getTotalLevel();
			}
			case "scourge_charisma": {
				return charisma.getTotalLevel();
			}
		}
		return -1; // shouldn't happen
	}
}

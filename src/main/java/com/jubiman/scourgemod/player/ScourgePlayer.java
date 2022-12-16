package com.jubiman.scourgemod.player;

import com.jubiman.customplayerlib.CustomPlayer;
import com.jubiman.scourgemod.player.level.LevelBase;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;

public class ScourgePlayer extends CustomPlayer {
	private int mana;

	private int strength;
	private int dexterity;
	private int intelligence;
	private int vitality;
	private int charisma;
	private int skillPoints;
	private final LevelBase playerLevel;

	public ScourgePlayer(long auth) {
		super(auth);
		mana = 100;
		strength = 1;
		dexterity = 1;
		intelligence = 1;
		vitality = 1;
		charisma = 1;

		skillPoints = 0;
		playerLevel = new LevelBase();
	}

	@Override
	public void addSaveData(SaveData save) {
		SaveData player = generatePlayerSave();
		playerLevel.save("playerLevel", player);
		player.addInt("mana", mana);

		player.addInt("str", strength);
		player.addInt("dex", dexterity);
		player.addInt("int", intelligence);
		player.addInt("vit", vitality);
		player.addInt("chr", charisma);

		player.addInt("sp", skillPoints);
		save.addSaveData(player);
	}

	@Override
	public void load(LoadData data) {
		playerLevel.load(data.getLoadDataByName("playerLevel").get(0));
		mana = data.getInt("mana");

		strength = data.getInt("str");
		dexterity = data.getInt("dex");
		intelligence = data.getInt("int");
		vitality = data.getInt("vit");
		charisma = data.getInt("chr");

		skillPoints = data.getInt("sp");
	}

	public void tick(Server server) {
		PlayerMob player = server.getPlayerByAuth(auth);
		if (!player.buffManager.hasBuff("scourge_vitality"))
			player.addBuff(new ActiveBuff("scourge_vitality", player, 1000000, null), true);
	}

	public void clientTick(Client client) {
		PlayerMob player = client.getPlayerByAuth(auth);
		if (!player.buffManager.hasBuff("scourge_vitality")) {
			player.addBuff(new ActiveBuff("scourge_vitality", player, 1000000, null), true);
			ScourgePlayers.forceBuffTick = true;
		}
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}

	public LevelBase getPlayerLevel() {
		return playerLevel;
	}

	public float getVitalityHPBoost() {
		return (float) (0.01 * vitality * vitality - 0.01 * vitality);
	}

	public float getVitalityArmorBoost() {
		return (float) (0.005 * vitality * vitality - 0.005 * vitality);
	}
}

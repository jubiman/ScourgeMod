package com.jubiman.scourgemod.player;

import com.jubiman.customplayerlib.CustomPlayerTickable;
import com.jubiman.scourgemod.network.packet.SyncLevelPacket;
import com.jubiman.scourgemod.player.level.LevelBase;
import com.jubiman.scourgemod.player.mana.Mana;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;
import com.jubiman.scourgemod.player.stat.*;
import necesse.engine.localization.Localization;
import necesse.engine.network.client.Client;
import necesse.engine.network.packet.PacketMobBuffRemove;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class ScourgePlayer extends CustomPlayerTickable {
	private final Mana mana;
	private PlayerClass playerClass;
	private Strength strength;
	private Dexterity dexterity;
	private Intelligence intelligence;
	private Vitality vitality;
	private Charisma charisma; // TODO: reduce NPC buy prices and increase sell prices (probably a patch)
	private int skillPoints;
	private final LevelBase playerLevel;

	public ScourgePlayer(long auth) {
		super(auth);
		mana = new Mana();
		strength = new Strength();
		dexterity = new Dexterity();
		intelligence = new Intelligence();
		vitality = new Vitality();
		charisma = new Charisma();

		skillPoints = 0;
		playerLevel = new LevelBase();
	}

	@Override
	public void addSaveData(@NotNull SaveData save) {
		SaveData player = generatePlayerSave();
		playerLevel.save("playerLevel", player);
		if (playerClass != null)
			playerClass.save(player);
		mana.save(player);

		player.addInt("str", getStrength());
		player.addInt("dex", getDexterity());
		player.addInt("int", getIntelligence());
		player.addInt("vit", getVitality());
		player.addInt("cha", getCharisma());

		player.addInt("sp", skillPoints);
		save.addSaveData(player);
	}

	@Override
	public void loadEnter(@NotNull LoadData data) {
		playerLevel.load(data.getLoadDataByName("playerLevel").get(0));

		try {
			LoadData playerClassData = data.getLoadDataByName("playerClass").get(0);
			playerClass = (PlayerClass) Class.forName("com.jubiman.scourgemod.player.playerclass." + playerClassData.getUnsafeString("playerClassName")).getConstructor(ScourgePlayer.class).newInstance(this);
			//playerClass.load(playerClassData);
		} catch (IndexOutOfBoundsException ignored) {
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
				 IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IndexOutOfBoundsException ignored) {}

		mana.load(data.getLoadDataByName("mana").get(0));

		strength = new Strength(data.getInt("str"));
		dexterity = new Dexterity(data.getInt("dex"));
		intelligence = new Intelligence(data.getInt("int"));
		vitality = new Vitality(data.getInt("vit"));
		charisma = new Charisma(data.getInt("cha"));

		skillPoints = data.getInt("sp");
	}

	@Override
	public void loadExit(LoadData data) {
	}

	@Override
	public void serverTick(@NotNull Server server) {
		playerTick(server.getPlayerByAuth(auth));
		mana.serverTick(server, this);
	}

	private void playerTick(PlayerMob player) {
		for (String string_id : ScourgePlayersHandler.scourgeBuffs)
			if (!player.buffManager.hasBuff("scourge_" + string_id))
				player.addBuff(new ActiveBuff("scourge_" + string_id, player, 1000000, null), true);
		if (playerClass != null && !player.buffManager.hasBuff(playerClass.getBuffStringId()))
			player.addBuff(new ActiveBuff(playerClass.getBuffStringId(), player, 1000000, null), true);
	}

	@Override
	public void clientTick(@NotNull Client client) {
		playerTick(client.getPlayerByAuth(auth));
		mana.clientTick(client, this);
	}

	public void resetStats() {
		skillPoints += getStrength() + getDexterity() + getIntelligence() + getVitality() + getCharisma() - 5;

		strength.reset();
		dexterity.reset();
		intelligence.reset();
		vitality.reset();
		charisma.reset();
	}

	public Mana getMana() {
		return mana;
	}

	public int getMaxMana() {
		return mana.getMaxMana();
	}

	public PlayerClass getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(PlayerClass playerClass, @NotNull PlayerMob player) {
		if (this.playerClass != null) {
			player.buffManager.removeBuff(this.playerClass.getBuffStringId(), true);
			player.getServerClient().sendPacket(new PacketMobBuffRemove(player.getUniqueID(), BuffRegistry.getBuffID(this.playerClass.getBuffStringId())));
		}
		this.playerClass = playerClass;
	}

	public int getStrength() {
		return strength.getLevel();
	}
	public Strength getStrengthObject() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength.setLevel(strength);
	}

	public int getDexterity() {
		return dexterity.getLevel();
	}
	public Dexterity getDexterityObject() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity.setLevel(dexterity);
	}

	public int getIntelligence() {
		return intelligence.getLevel();
	}
	public Intelligence getIntelligenceObject() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence.setLevel(intelligence);
	}

	public int getVitality() {
		return vitality.getLevel();
	}
	public Vitality getVitalityObject() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality.setLevel(vitality);
	}

	public int getCharisma() {
		return charisma.getLevel();
	}
	public Charisma getCharismaObject() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma.setLevel(charisma);
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

	public boolean useMana(int mana) { // TODO: maybe display message/sound of no mana
		return this.mana.useMana(mana);
	}

	public boolean addExp(int exp) {
		return playerLevel.addExp(exp);
	}

	public boolean addExp(int exp, @NotNull ServerClient serverClient) {
		boolean upped = playerLevel.addExp(exp);
		serverClient.sendPacket(new SyncLevelPacket(this));
		return upped;
	}

	public boolean addExp(int exp, @NotNull Server server) {
		boolean upped = playerLevel.addExp(exp);
		server.network.sendToAllClients(new SyncLevelPacket(this));
		return upped;
	}

	public int getLevel() {
		return playerLevel.getLevel();
	}

	public String getClassName() {
		if (playerClass != null)
			return playerClass.getDisplayName();
		return Localization.translate("playerclass", "empty");
	}

	public int getStatFromBuffID(@NotNull String stringID) {
		switch (stringID) {
			case "scourge_vitality": {
				return getVitality();
			}
			case "scourge_dexterity": {
				return getDexterity();
			}
			case "scourge_intelligence": {
				return getIntelligence();
			}
			case "scourge_strength": {
				return getStrength();
			}
			case "scourge_charisma": {
				return getCharisma();
			}
		}
		return -1; // shouldn't happen
	}

	public Stat getStatObjectFromBuffID(@NotNull String stringID) {
		switch (stringID) {
			case "scourge_vitality": {
				return vitality;
			}
			case "scourge_dexterity": {
				return dexterity;
			}
			case "scourge_intelligence": {
				return intelligence;
			}
			case "scourge_strength": {
				return strength;
			}
			case "scourge_charisma": {
				return charisma;
			}
		}
		return null; // shouldn't happen
	}
}

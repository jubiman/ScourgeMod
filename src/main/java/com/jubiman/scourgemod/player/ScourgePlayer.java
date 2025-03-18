package com.jubiman.scourgemod.player;

import com.jubiman.customdatalib.api.Savable;
import com.jubiman.customdatalib.player.CustomPlayer;
import com.jubiman.scourgemod.network.packet.PacketSyncLevel;
import com.jubiman.scourgemod.player.level.LevelBase;
import com.jubiman.scourgemod.player.playerclass.EmptyClass;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;
import com.jubiman.scourgemod.player.playerclass.mage.MageClass;
import com.jubiman.scourgemod.player.stat.*;
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

public class ScourgePlayer extends CustomPlayer implements Savable {
	private PlayerClass playerClass;
	private final Strength strength;
	private final Dexterity dexterity;
	private final Intelligence intelligence;
	private final Vitality vitality;
	private final Charisma charisma; // TODO: reduce NPC buy prices and increase sell prices (probably a patch)
	private final LevelBase playerLevel;
	private int statPoints;

	public ScourgePlayer(long auth) {
		super(auth);
		//mana = new Mana();
		strength = new Strength();
		dexterity = new Dexterity();
		intelligence = new Intelligence();
		vitality = new Vitality();
		charisma = new Charisma();

		statPoints = 0;
		playerLevel = new LevelBase();
		playerClass = new EmptyClass(this);
	}

	@Override
	public void addSaveData(@NotNull SaveData save) {
		playerLevel.save("playerLevel", save);
		playerClass.save(save);

		save.addInt("str", getStrength());
		save.addInt("dex", getDexterity());
		save.addInt("int", getIntelligence());
		save.addInt("vit", getVitality());
		save.addInt("cha", getCharisma());

		save.addInt("sp", statPoints);
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
		}

		strength.setLevel(data.getInt("str"));
		dexterity.setLevel(data.getInt("dex"));
		intelligence.setLevel(data.getInt("int"));
		vitality.setLevel(data.getInt("vit"));
		charisma.setLevel(data.getInt("cha"));


		statPoints = data.getInt("sp");
	}

	@Override
	public void loadExit(LoadData data) {
	}

	@Override
	public void serverTick(@NotNull Server server) {
		PlayerMob player = server.getPlayerByAuth(auth);
		if (player.getLevel() != null)
			for (String string_id : ScourgePlayersHandler.scourgeBuffs)
				if (!player.buffManager.hasBuff("scourge_" + string_id))
					player.addBuff(new ActiveBuff("scourge_" + string_id, player, 1000000, null), true);

		if (!(playerClass instanceof EmptyClass) && !player.buffManager.hasBuff(playerClass.getBuffStringId()))
			player.addBuff(new ActiveBuff(playerClass.getBuffStringId(), player, 1000000, null), true);

		// If player is a mage, regenerate 1% missing mana every second
		if (server.tickManager().isFirstGameTickInSecond() && playerClass instanceof MageClass) {
			double ln = Math.log(this.getPlayerLevel().getLevel() + 1);
			float missingManaPercent = (int) (1 + .1f * ln * ln); // goes to 100% at e^(3 sqrt(110)) which is a number with 14 digits, realistically doesn't go much higher than 2-3%
			float currentMana = player.getMana();
			float maxMana = player.getMaxMana();
			player.setMana(currentMana + (maxMana - currentMana) * (missingManaPercent / 100));
		}
	}

//	@Override
//	public void clientTick(@NotNull Client client) {
//		float currentMana = client.getPlayer().getMana();
//		float maxMana = client.getPlayer().getMaxMana();
//		float missingManaPercent = 1 - (currentMana / maxMana); // 0.0 - 1.0
//
//		client.getPlayer().setMana(currentMana + maxMana * missingManaPercent);
//
//		//playerTick(client.getPlayerByAuth(auth));
//		//mana.clientTick(client, this);
//	}

	public void resetStats() {
		calcStatPoints();

		strength.reset();
		dexterity.reset();
		intelligence.reset();
		vitality.reset();
		charisma.reset();
	}

	public PlayerClass getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(PlayerClass playerClass, @NotNull PlayerMob player) {
		if (!(this.playerClass instanceof EmptyClass)) {
			player.buffManager.removeBuff(this.playerClass.getBuffStringId(), true);
			player.getServerClient().sendPacket(new PacketMobBuffRemove(player.getUniqueID(), BuffRegistry.getBuffID(this.playerClass.getBuffStringId())));
		}
		this.playerClass = playerClass;
	}

	public int getStrength() {
		return strength.getTotalLevel();
	}
	public Strength getStrengthObject() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength.setLevel(strength);
	}

	public int getDexterity() {
		return dexterity.getTotalLevel();
	}
	public Dexterity getDexterityObject() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity.setLevel(dexterity);
	}

	public int getIntelligence() {
		return intelligence.getTotalLevel();
	}
	public Intelligence getIntelligenceObject() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence.setLevel(intelligence);
	}

	public int getVitality() {
		return vitality.getTotalLevel();
	}
	public Vitality getVitalityObject() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality.setLevel(vitality);
	}

	public int getCharisma() {
		return charisma.getTotalLevel();
	}
	public Charisma getCharismaObject() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma.setLevel(charisma);
	}

	public int getStatPoints() {
		return statPoints;
	}

	public void setStatPoints(int statPoints) {
		this.statPoints = statPoints;
	}

	public LevelBase getPlayerLevel() {
		return playerLevel;
	}

	public boolean addExp(int exp, @NotNull ServerClient serverClient) {
		boolean upped = playerLevel.addExp(exp);
		if (upped) {
			calcStatPoints();
		}
		serverClient.sendPacket(new PacketSyncLevel(this));
		return upped;
	}

	public int getLevel() {
		return playerLevel.getLevel();
	}

	public String getClassName() {
		return playerClass.getDisplayName();
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

	public void onDeath() {
		// Remove all gemstone boosts
		strength.resetBoost();
		dexterity.resetBoost();
		intelligence.resetBoost();
		vitality.resetBoost();
		charisma.resetBoost();
	}

	public Stat getStat(Stat.StatType stat) {
		switch (stat) {
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

	public boolean levelStat(Stat.StatType stat) {
		if (statPoints > 0) {
			getStat(stat).addLevel(1);
			statPoints--;
			return true;
		}
		return false;
	}

	public void calcStatPoints() {
		statPoints = getLevel() * 3 - getStrengthObject().getLevel() - getDexterityObject().getLevel() - getIntelligenceObject().getLevel() - getVitalityObject().getLevel() - getCharismaObject().getLevel() + 5;
	}
}

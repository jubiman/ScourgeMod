package com.jubiman.scourgemod.player;

import com.jubiman.customdatalib.api.ClientTickable;
import com.jubiman.customdatalib.api.Savable;
import com.jubiman.customdatalib.player.CustomPlayer;
import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;
import com.jubiman.scourgemod.network.packet.PacketSyncLevel;
import com.jubiman.scourgemod.player.level.LevelBase;
import com.jubiman.scourgemod.player.mana.Mana;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;
import com.jubiman.scourgemod.player.playerclass.mage.MageClass;
import com.jubiman.scourgemod.player.stat.*;
import necesse.engine.GameLog;
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
import java.lang.reflect.Method;
import java.time.chrono.ChronoLocalDate;
import java.util.function.Consumer;

public class ScourgePlayer extends CustomPlayer implements Savable {
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
		//mana = new Mana();
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
		playerLevel.save("playerLevel", save);
		if (playerClass != null)
			playerClass.save(save);
		//mana.save(save);



		save.addInt("str", getStrength());
		save.addInt("dex", getDexterity());
		save.addInt("int", getIntelligence());
		save.addInt("vit", getVitality());
		save.addInt("cha", getCharisma());

		save.addInt("sp", skillPoints);
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

		//mana.load(data.getLoadDataByName("mana").get(0));

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
		PlayerMob player = server.getPlayerByAuth(auth);
		if (player.getLevel() != null)
			for (String string_id : ScourgePlayersHandler.scourgeBuffs)
				if (!player.buffManager.hasBuff("scourge_" + string_id))
					player.addBuff(new ActiveBuff("scourge_" + string_id, player, 1000000, null), true);

		if (playerClass != null && !player.buffManager.hasBuff(playerClass.getBuffStringId()))
			player.addBuff(new ActiveBuff(playerClass.getBuffStringId(), player, 1000000, null), true);

		// If player is a mage, regenerate 1% missing mana every second
		if (server.tickManager().isFirstGameTickInSecond() && playerClass != null && playerClass instanceof MageClass) {
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
		skillPoints += getStrength() + getDexterity() + getIntelligence() + getVitality() + getCharisma() - 5;

		strength.reset();
		dexterity.reset();
		intelligence.reset();
		vitality.reset();
		charisma.reset();
	}

	/*public Mana getMana() {
		return mana;
	}

	public int getMaxMana() {
		return mana.getMaxMana();
	}*/

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

	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}

	public LevelBase getPlayerLevel() {
		return playerLevel;
	}

	/*public boolean useMana(int mana) { // TODO: maybe display message/sound of no mana
		return this.mana.useMana(mana);
	}*/

	public boolean addExp(int exp) {
		return playerLevel.addExp(exp);
	}

	public boolean addExp(int exp, @NotNull ServerClient serverClient) {
		boolean upped = playerLevel.addExp(exp);
		serverClient.sendPacket(new PacketSyncLevel(this));
		return upped;
	}

	public boolean addExp(int exp, @NotNull Server server) {
		boolean upped = playerLevel.addExp(exp);
		server.network.sendToAllClients(new PacketSyncLevel(this));
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

	public void addGemstoneBoost(String gemstoneType) {
		try {
			doGemstoneAction(gemstoneType, Stat.class.getMethod("addGemstoneBoost", GemstoneQuality.class));
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private void doGemstoneAction(String gemstoneType, Method method) {
		// TODO: maybe change the way gemstones work with a quality field/GND field
		try {
			switch (gemstoneType) {
				case "scourge_rough_topaz_gemstone": {
					method.invoke(charisma, GemstoneQuality.ROUGH);
					break;
				}
				case "scourge_flawed_topaz_gemstone": {
					method.invoke(charisma, GemstoneQuality.FLAWED);
					break;
				}
				case "scourge_fine_topaz_gemstone": {
					method.invoke(charisma, GemstoneQuality.FINE);
					break;
				}
				case "scourge_flawless_topaz_gemstone": {
					method.invoke(charisma, GemstoneQuality.FLAWLESS);
					break;
				}
				case "scourge_perfect_topaz_gemstone": {
					method.invoke(charisma, GemstoneQuality.PERFECT);
					break;
				}
				case "charisma": {
					//charisma.addGemstoneBoost();
					break;
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public void removeGemstoneBoost(String gemstoneType) {
		try {
			doGemstoneAction(gemstoneType, Stat.class.getMethod("removeGemstoneBoost", GemstoneQuality.class));
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public void onDeath() {
		// Remove all gemstone boosts
		strength.resetBoost();
		dexterity.resetBoost();
		intelligence.resetBoost();
		vitality.resetBoost();
		charisma.resetBoost();
	}
}

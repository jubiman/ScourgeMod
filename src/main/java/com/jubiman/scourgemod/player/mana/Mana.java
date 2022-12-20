package com.jubiman.scourgemod.player.mana;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.mage.MageClass;
import necesse.engine.Screen;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.gameTexture.GameTexture;

public class Mana {
	private int mana;
	private int maxMana;
	private int missingManaPercent;

	public static GameTexture barTexture;
	public static GameTexture currBarTexture;
	public static GameTexture outOfManaBarTexture;
	public static GameTexture outlineLeftTexture;
	public static GameTexture outlineMidTexture;
	public static GameTexture outlineRightTexture;

	private int lastKnownIntelligence = 1;
	private boolean mageClassApplied = false;

	public Mana() {
		this(100);
	}

	public Mana(int mana) {
		this(mana, mana);
	}

	public Mana(int mana, int maxMana) {
		this.mana = mana;
		this.maxMana = maxMana;
		this.missingManaPercent = 0;
	}

	public static void loadTextures() {
		barTexture = GameTexture.fromFile("mana/mana_bar");
		currBarTexture = GameTexture.fromFile("mana/mana_bar");
		outOfManaBarTexture = GameTexture.fromFile("mana/no_mana_bar");
		// Outlines
		outlineLeftTexture = GameTexture.fromFile("mana/mana_bar_outline_left");
		outlineMidTexture = GameTexture.fromFile("mana/mana_bar_outline_middle");
		outlineRightTexture = GameTexture.fromFile("mana/mana_bar_outline_right");
	}

	public void save(SaveData save) {
		SaveData data = new SaveData("mana");
		data.addInt("currentMana", mana);
		data.addInt("maxMana", maxMana);
		save.addSaveData(data);
	}

	public void load(LoadData data) {
		mana = data.getInt("currentMana");
		maxMana = data.getInt("maxMana");
	}

	public int getMana() {
		return mana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int intelligence) {
		this.maxMana = (int) ((100 + (0.5 * intelligence * (intelligence - 1)))); // TODO: check formula
		mageClassApplied = false;
		// TODO: maybe send update packet?
	}

	public void draw() {
		if (mana == 0) {
			currBarTexture = outOfManaBarTexture;
		} else {
			currBarTexture = barTexture;
		}
		int barWidth = 410;
		int barX = Screen.getWindowWidth() / 2 - barWidth / 2;
		int barHeight = 12;
		int barY = Screen.getWindowHeight() - barHeight - 6;
		currBarTexture.initDraw().size(Math.round((float) mana / this.maxMana * barWidth), barHeight).draw(barX, barY);
		outlineLeftTexture.initDraw().size(32, barHeight + 8).draw(barX - 4, barY - 4);
		outlineMidTexture.initDraw().size(barWidth + 8, barHeight + 8).draw(barX - 4, barY - 4);
		outlineRightTexture.initDraw().size(32, barHeight + 8).draw(barX + 382, barY - 4);
		String amountString = Math.round(mana) + " / " + this.maxMana;
		FontOptions manaFontOptions = (new FontOptions(15)).color(200, 200, 200).outline();
		int width = FontManager.bit.getWidthCeil(amountString, manaFontOptions);
		FontManager.bit.drawString(Screen.getWindowWidth() / 2.0F + 33.0F - width, barY, amountString, manaFontOptions);
	}

	public void serverTick(Server server, ScourgePlayer player) {
		if (lastKnownIntelligence != player.getIntelligence() || player.getPlayerClass() instanceof MageClass)
			setMaxMana(lastKnownIntelligence = player.getIntelligence());
		if (player.getPlayerClass() instanceof MageClass) {
			if (!mageClassApplied) {
				maxMana += maxMana * ((MageClass) player.getPlayerClass()).getMaxManaBuff();
				mageClassApplied = true;
			}
		} else mageClassApplied = false;
		if (player.getPlayerClass() instanceof MageClass) {
			double ln = Math.log(player.getPlayerLevel().getLevel() + 1);
			missingManaPercent = (int) (1 + ln * ln); // goes to 100 at 20950, realistically doesn't go much higher than 20-30%
		} else missingManaPercent = 0;
		if (server.tickManager().isFirstGameTickInSecond())
			mana = Math.min(Math.max(mana + 10 + maxMana / 100 + ((maxMana - mana) * (missingManaPercent / 100)), 0), maxMana); // regenerate 10 + 1 % max mana per second
	}

	public void clientTick(Client client, ScourgePlayer player) {

	}

	public boolean useMana(int mana) {
		if (this.mana - mana > 0) {
			this.mana -= mana;
			return true;
		}
		return false;
	}
}

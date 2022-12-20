package com.jubiman.scourgemod.player.playerclass;

import com.jubiman.scourgemod.player.ScourgePlayer;
import necesse.engine.localization.Localization;
import necesse.engine.save.SaveData;

public abstract class PlayerClass {
	private final String buffStringId;
	protected final String className;
	protected final String displayName;
	protected final ScourgePlayer player;

	public PlayerClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		this.buffStringId = buffStringId;
		this.displayName = displayName;
		this.player = player;
		this.className = className;
	}

	protected int getLevel() {
		return player.getPlayerLevel().getLevel();
	}

	public String getBuffStringId() {
		return buffStringId;
	}

	public abstract float getOtherDamageTypesDebuff();

	public String getDisplayName() {
		return Localization.translate("playerclass", displayName);
	}

	public void save(SaveData save) {
		SaveData data = new SaveData("playerClass");
		data.addUnsafeString("buffStringId", buffStringId);
		data.addUnsafeString("playerClassName", className);
		save.addSaveData(data);
	}
}

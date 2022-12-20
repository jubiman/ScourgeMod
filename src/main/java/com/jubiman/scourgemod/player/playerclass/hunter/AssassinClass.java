package com.jubiman.scourgemod.player.playerclass.hunter;

import com.jubiman.scourgemod.player.ScourgePlayer;
import necesse.engine.save.SaveData;

public class AssassinClass extends HunterClass {
	public AssassinClass(ScourgePlayer player) {
		super("scourge_assassin", "assassin", player, "hunter.AssassinClass");
	}

	@Override
	public void save(SaveData save) {
		SaveData data = new SaveData("playerClass");
		super.save(data);
		data.addUnsafeString("playerClassName", "hunter.AssassinClass");
		save.addSaveData(data);
	}

	public float getDamageBuff() {
		return (float) (0.03 * getLevel() * (getLevel() -1 ));
	}

	@Override
	public float getAttackSpeedBuff() {
		return super.getAttackSpeedBuff() * 7;
	}

	@Override
	public float getArmorPenBuff() {
		return super.getArmorPenBuff() * 10;
	}

	@Override
	public float getCritDamageBuff() {
		return super.getCritDamageBuff() * 7;
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.3f;
	}
}

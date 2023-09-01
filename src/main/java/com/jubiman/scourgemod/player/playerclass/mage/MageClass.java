package com.jubiman.scourgemod.player.playerclass.mage;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;

public class MageClass extends PlayerClass {

	public MageClass(ScourgePlayer player) {
		super("scourge_mage", "mage", player, "mage.MageClass");
	}

	public MageClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		super(buffStringId, displayName, player, className);
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.7f;
	}

	public float getDamageBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}

	public float getAttackSpeedBuff() {
		return (float) (0.0003 * getLevel() * (getLevel() - 1));
	}

	public float getSummonsSpeedBuff() {
		return (float) (0.005 * getLevel() * (getLevel() - 1));
	}

	public float getSummonsTargetRangeBuff() {
		return (float) (0.0007 * getLevel() * (getLevel() - 1));
	}

	/**
	 * Returns the mana regen buff in percent
	 * @return the mana regen buff in percent
	 */
	public float getMaxManaBuff() {
		return (float) (0.033 * getLevel() * (getLevel() - 1));
	}

	public float getManaRegenBuff() {
		return (float) (0.0007 * getLevel() * (getLevel() - 1));
	}
}

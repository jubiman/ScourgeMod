package com.jubiman.scourgemod.item.gemstone;

public enum GemstoneQuality {
	// TODO: Balance all the values
	ROUGH(1, 1, 1, 1, 1),
	FLAWED(2, 2, 2, 2, 2),
	FINE(4, 4, 4, 4, 4),
	FLAWLESS(8, 8, 8, 8, 8),
	PERFECT(32, 32, 32, 32, 32);

	public final int intelligence;
	public final int strength;
	public final int dexterity;
	public final int vitality;
	public final int charisma;

	GemstoneQuality(int intelligence, int strength, int dexterity, int vitality, int charisma) {
		this.intelligence = intelligence;
		this.strength = strength;
		this.dexterity = dexterity;
		this.vitality = vitality;
		this.charisma = charisma;
	}

}

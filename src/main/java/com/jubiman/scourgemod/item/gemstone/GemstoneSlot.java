package com.jubiman.scourgemod.item.gemstone;

import necesse.engine.network.gameNetworkData.GNDItemMap;

public class GemstoneSlot implements Cloneable {
	public final GemstoneSlotType type;
	private GemstoneItem gemstone;

	public GemstoneSlot(GemstoneSlotType type) {
		this.type = type;
	}

	public GemstoneItem getGemstone() {
		return gemstone;
	}

	public GemstoneSlot setGemstone(GemstoneItem gemstone) {
		this.gemstone = gemstone;
		return this;
	}

	public GNDItemMap getGndData() {
		GNDItemMap gndData = new GNDItemMap();
		gndData.setString("slotType", type.name());
		if (gemstone != null) {
			gndData.setString("gemstoneType", gemstone.getStringID());
		}
		return gndData;
	}

	@Override
	public GemstoneSlot clone() {
		try {
			GemstoneSlot clone = (GemstoneSlot) super.clone();
			if (gemstone != null) {
				clone.gemstone = gemstone.clone();
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	public boolean isGemstoneTypeValid(GemstoneType type) {
		switch (type) {
			case INT:
				return this.type == GemstoneSlotType.MAGIC || this.type == GemstoneSlotType.COMBAT;
			case STR:
				return this.type == GemstoneSlotType.COMBAT || this.type == GemstoneSlotType.PHYSICAL;
			case DEX:
			case VIT:
				return this.type == GemstoneSlotType.COMBAT || this.type == GemstoneSlotType.PHYSICAL || this.type == GemstoneSlotType.DEFENSE;
			case CHA:
				return this.type == GemstoneSlotType.CHARISMA;
			default:
				return false;
		}
	}

	public enum GemstoneSlotType {
		COMBAT,		// STR and DEX and INT and VIT
		PHYSICAL,	// STR and VIT and DEX
		MAGIC,		// INT and CHA
		DEFENSE,	// VIT and DEX
		CHARISMA	// CHA
	}
}

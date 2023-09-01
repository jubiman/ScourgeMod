package com.jubiman.scourgemod;

import com.jubiman.scourgemod.item.gemstone.GemstoneSlot;
import necesse.gfx.gameTexture.GameTexture;

public class Textures {
	public static class UI {
		public static GameTexture inventoryslot_icon_combat;
		public static GameTexture inventoryslot_icon_defense;
		public static GameTexture inventoryslot_icon_physical;
		public static GameTexture inventoryslot_icon_magic;
		public static GameTexture inventoryslot_icon_charisma;

		public static GameTexture getGemstoneSlotTexture(GemstoneSlot.GemstoneSlotType type) {
			switch (type) {
				case COMBAT:
					return inventoryslot_icon_combat;
				case DEFENSE:
					return inventoryslot_icon_defense;
				case PHYSICAL:
					return inventoryslot_icon_physical;
				case MAGIC:
					return inventoryslot_icon_magic;
				case CHARISMA:
					return inventoryslot_icon_charisma;
				default:
					throw new AssertionError();
			}
		}
	}

	public static void initTextures() {
		UI.inventoryslot_icon_combat = GameTexture.fromFile("ui/inventoryslot_icon_combat");
		UI.inventoryslot_icon_defense = GameTexture.fromFile("ui/inventoryslot_icon_defense");
		UI.inventoryslot_icon_physical = GameTexture.fromFile("ui/inventoryslot_icon_physical");
		UI.inventoryslot_icon_magic = GameTexture.fromFile("ui/inventoryslot_icon_magic");
		UI.inventoryslot_icon_charisma = GameTexture.fromFile("ui/inventoryslot_icon_charisma");
	}
}

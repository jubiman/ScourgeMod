package com.jubiman.scourgemod;

import necesse.engine.Settings;
import necesse.gfx.gameTexture.GameTexture;
import necesse.gfx.ui.ButtonIcon;


public class Textures {
	public static class UI {
		public static GameTexture inventoryslot_icon_combat;
		public static GameTexture inventoryslot_icon_defense;
		public static GameTexture inventoryslot_icon_physical;
		public static GameTexture inventoryslot_icon_magic;
		public static GameTexture inventoryslot_icon_charisma;
		public static ButtonIcon stat_icon;
	}

	public static void initTextures() {
		UI.inventoryslot_icon_combat = GameTexture.fromFile("ui/inventoryslot_icon_combat");
		UI.inventoryslot_icon_defense = GameTexture.fromFile("ui/inventoryslot_icon_defense");
		UI.inventoryslot_icon_physical = GameTexture.fromFile("ui/inventoryslot_icon_physical");
		UI.inventoryslot_icon_magic = GameTexture.fromFile("ui/inventoryslot_icon_magic");
		UI.inventoryslot_icon_charisma = GameTexture.fromFile("ui/inventoryslot_icon_charisma");
		UI.stat_icon = new ButtonIcon(Settings.UI, "");
		UI.stat_icon.texture = GameTexture.fromFile("ui/scourge_stat_icon");
	}
}

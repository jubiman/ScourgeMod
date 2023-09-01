package com.jubiman.scourgemod.form;

import com.jubiman.scourgemod.Textures;
import com.jubiman.scourgemod.item.gemstone.GemstoneSlot;
import necesse.engine.localization.Localization;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.components.containerSlot.FormContainerSlot;
import necesse.gfx.gameTooltips.GameTooltips;
import necesse.gfx.gameTooltips.StringTooltips;

public class FormContainerGemstoneSlot extends FormContainerSlot {
	private final GemstoneSlot.GemstoneSlotType type;
	public FormContainerGemstoneSlot(Client client, int containerSlotIndex, int x, int y, GemstoneSlot.GemstoneSlotType type) {
		super(client, containerSlotIndex, x, y);
		this.setDecal(Textures.UI.getGemstoneSlotTexture(type));
		this.type = type;
	}

	public GameTooltips getClearTooltips() {
		return new StringTooltips(Localization.translate("itemtooltip", "gemstoneslot_" + type.name().toLowerCase()));
	}
}

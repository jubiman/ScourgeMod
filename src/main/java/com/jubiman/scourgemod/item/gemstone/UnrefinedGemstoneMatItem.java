package com.jubiman.scourgemod.item.gemstone;

import necesse.inventory.item.Item;
import necesse.inventory.item.matItem.MatItem;

public abstract class UnrefinedGemstoneMatItem extends MatItem {
	public UnrefinedGemstoneMatItem(int stacksize, Rarity rarity) {
		super(stacksize, rarity);
		setItemCategory("materials", "minerals");
	}

	/**
	 * Refines the gemstone into a random quality gemstone.
	 * @return The refined gemstone that this unrefined gemstone can be refined into.
	 */
	public abstract Item getRefinedGemstone();
}

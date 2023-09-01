package com.jubiman.scourgemod.item.gemstone.topaz;

import com.jubiman.scourgemod.item.gemstone.UnrefinedGemstoneMatItem;
import com.jubiman.scourgemod.util.WeightedRandomList;
import necesse.engine.registries.ItemRegistry;
import necesse.inventory.item.Item;

public class TopazUnrefinedGemstoneMatItem extends UnrefinedGemstoneMatItem {
	private static final WeightedRandomList<String> refinedGemstones = new WeightedRandomList<>();

	static {
		refinedGemstones.add("scourge_rough_topaz_gemstone", 5);
		// TODO: Add more refined gemstones.
	}

	public TopazUnrefinedGemstoneMatItem(int stacksize, Rarity rarity) {
		super(stacksize, rarity);
	}

	@Override
	public Item getRefinedGemstone() {
		return ItemRegistry.getItem(refinedGemstones.getRandom());
	}
}

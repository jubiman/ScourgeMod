package com.jubiman.scourgemod.item.gemstone;

import com.jubiman.scourgemod.player.stat.Stat;
import necesse.inventory.item.Item;

public class GemstoneItem extends Item implements Cloneable {
	public final Class<? extends Stat> stat;
	public final GemstoneQuality quality;
	public final GemstoneType type;

	public GemstoneItem(Class<? extends Stat> stat, GemstoneQuality quality, GemstoneType type, Rarity rarity) {
		super(500);
		this.type = type;
		this.rarity = rarity;
		this.stat = stat;
		this.quality = quality;
	}

	@Override
	public GemstoneItem clone() {
		try {
			return (GemstoneItem) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}

package com.jubiman.scourgemod.registry;

import com.jubiman.scourgemod.item.gemstone.GemstoneItem;
import com.jubiman.scourgemod.item.gemstone.GemstoneSlot;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.registries.ItemRegistry;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GemstoneSlotRegistry {
	private static final HashMap<String, GemstoneSlotList> registry = new HashMap<>(); // TODO make static or smth

	static {
		register("ancientfossilchestplate",
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.PHYSICAL,
				GemstoneSlot.GemstoneSlotType.CHARISMA
		);
		register("ancientfossilhelmet",
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.PHYSICAL,
				GemstoneSlot.GemstoneSlotType.CHARISMA
		);
		register("ancientfossilmask",
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.MAGIC,
				GemstoneSlot.GemstoneSlotType.CHARISMA
		);
		register("ancientfossilboots",
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.COMBAT,
				GemstoneSlot.GemstoneSlotType.PHYSICAL,
				GemstoneSlot.GemstoneSlotType.CHARISMA
		);
	}

	public static void register(String key, GemstoneSlotList slots) {
		registry.put(key, slots);
	}

	public static void register(String key, GemstoneSlot.GemstoneSlotType... slots) {
		GemstoneSlot[] gemstoneSlots = new GemstoneSlot[slots.length];
		for (int i = 0; i < slots.length; i++) {
			gemstoneSlots[i] = new GemstoneSlot(slots[i]);
		}
		register(key, new GemstoneSlotList(Arrays.asList(gemstoneSlots)));
	}

	public static void register(String key, GemstoneSlot... slots) {
		register(key, new GemstoneSlotList(Arrays.asList(slots)));
	}

	public static GemstoneSlotList get(String key) {
		if (!registry.containsKey(key))
			return null;
		return registry.get(key).clone();
	}

	public static GemstoneSlotList getListFromGNDData(GNDItemMap gndData) {
		GNDItemMap gndItemMap = (GNDItemMap) gndData.getItem("gemstones");
		GemstoneSlot[] slots = new GemstoneSlot[gndItemMap.getMapSize()];
		for (int i = 0; i < gndItemMap.getMapSize(); i++) {
			GNDItemMap slotData = (GNDItemMap) gndItemMap.getItem(i);
			GemstoneSlot.GemstoneSlotType type = GemstoneSlot.GemstoneSlotType.valueOf(slotData.getString("slotType"));
			GemstoneSlot slot = new GemstoneSlot(type);
			if (slotData.hasKey("gemstoneType")) {
				slot.setGemstone((GemstoneItem) ItemRegistry.getItem(slotData.getString("gemstoneType")));
			}
			slots[i] = slot;
		}
		return new GemstoneSlotList(Arrays.asList(slots));
	}

	public static class GemstoneSlotList implements Cloneable {
		private final List<GemstoneSlot> slots;

		public GemstoneSlotList(List<GemstoneSlot> slots) {
			this.slots = slots;
		}

		public GNDItemMap toGNDItemArray() {
			// TODO: possibly revert to GNDItemArray when it supports GNDItemMap, for now use GNDItemMap as an array
			GNDItemMap items = new GNDItemMap();
			for (int i = 0; i < slots.size(); i++) {
				items.setItem(i, slots.get(i).getGndData());
			}
			return items;
		}

		public int size() {
			return slots.size();
		}

		@Override
		public GemstoneSlotList clone() {
			try {
				GemstoneSlotList clone = (GemstoneSlotList) super.clone();
				Collections.copy(clone.slots, slots);
				return clone;
			} catch (CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}

		public GemstoneSlot get(int index) {
			return slots.get(index);
		}
	}
}

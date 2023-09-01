package com.jubiman.scourgemod.util;

import java.util.LinkedList;

public class WeightedRandomList<T> {
	private final LinkedList<ListEntry> entries = new LinkedList<>();
	private int totalWeight = 0;

	@SafeVarargs
	public final WeightedRandomList<T> add(T... items) {
		for (T item : items) {
			entries.add(new ListEntry(item, 1));
		}
		totalWeight += items.length;
		return this;
	}

	public WeightedRandomList<T> add(T item, int weight) {
		entries.add(new ListEntry(item, weight));
		totalWeight += weight;
		return this;
	}

	public WeightedRandomList<T> add(T item) {
		add(item, 1);
		return this;
	}

	public T getRandom() {
		int random = (int) (Math.random() * totalWeight);
		for (ListEntry entry : entries) {
			random -= entry.weight;
			if (random <= 0) {
				return entry.item;
			}
		}
		return null;
	}

	private class ListEntry {
		private final T item;
		private final int weight;

		public ListEntry(T item, int weight) {
			this.item = item;
			this.weight = weight;
		}
	}
}

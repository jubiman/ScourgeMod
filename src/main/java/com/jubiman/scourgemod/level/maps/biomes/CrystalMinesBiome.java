package com.jubiman.scourgemod.level.maps.biomes;

import necesse.engine.network.server.Server;
import necesse.engine.world.WorldEntity;
import necesse.level.maps.Level;
import necesse.level.maps.biomes.Biome;
import necesse.level.maps.biomes.SnowBiome;

public abstract class CrystalMinesBiome extends Biome {

	public abstract Level getNewCaveLevel(int islandX, int islandY, int dimension, Server server, WorldEntity worldEntity);

	protected abstract static class BiomeDeepCaveLevel extends Level {
		public BiomeDeepCaveLevel(int islandX, int islandY, int dimension, Server server, WorldEntity worldEntity) {
			super(300, 300, islandX, islandY, dimension, true, server, worldEntity);
		}

		protected abstract void generateLevel();
	}

	protected abstract static class BiomeCaveLevel extends Level {
		public BiomeCaveLevel(int islandX, int islandY, int dimension, Server server, WorldEntity worldEntity) {
			super(300, 300, islandX, islandY, dimension, true, server, worldEntity);
			this.generateLevel();
		}

		protected abstract void generateLevel();
	}

	protected abstract static class BiomeIslandLevel extends Level {
		public BiomeIslandLevel(int islandX, int islandY, float islandSize, Server server, WorldEntity worldEntity) {
			super(300, 300, islandX, islandY, 0, false, server, worldEntity);
			this.generateLevel(islandSize);
		}

		protected abstract void generateLevel(float islandSize);
	}

	public CrystalMinesBiome() {
		//super(100, 100, islandX, islandY, dimension, false, server);
	}
}

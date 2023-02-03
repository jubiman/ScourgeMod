package com.jubiman.scourgemod.level.maps.biomes.CrystalMines;

import necesse.engine.network.server.Server;
import necesse.engine.world.WorldEntity;
import necesse.level.maps.Level;
import necesse.level.maps.biomes.snow.SnowBiome;

public class SnowCrystalMinesBiome extends SnowBiome {
	@Override
	public Level getNewSurfaceLevel(int islandX, int islandY, float islandSize, Server server, WorldEntity worldEntity) {
		System.out.println("THIS IS A CRYSTAL MINE!!!");
		return super.getNewSurfaceLevel(islandX, islandY, islandSize, server, worldEntity);
	}

	@Override
	public Level getNewCaveLevel(int islandX, int islandY, int dimension, Server server, WorldEntity worldEntity) {
		return new SnowCrystalMinesCaveLevel(islandX, islandY, dimension, worldEntity);
	}
}

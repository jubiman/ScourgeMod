package com.jubiman.scourgemod.level.world;

import com.jubiman.scourgemod.level.maps.biomes.CrystalMines.SnowCrystalMinesBiome;
import necesse.engine.network.server.Server;
import necesse.engine.util.LevelIdentifier;
import necesse.engine.world.WorldGenerator;
import necesse.level.maps.Level;
import necesse.level.maps.biomes.Biome;

public class ScourgeWorldGenerator extends WorldGenerator {
	@Override
	public Level getNewLevel(int x, int y, int dim, Server server) {
		System.out.println("YES! " + x + " " + y + " " + dim);
		if (x == -3612 && y == 534) {
			Biome biome = new SnowCrystalMinesBiome();
		/*Biome biome = null;
		if (server.world.levelManager.isLoaded(x, y, 0)) {
			biome = (server.world.levelManager.getLevel(x, y, 0)).biome;
		} else if (server.world.levelExists(x, y, 0)) {
			LoadData save = server.world.loadLevelScript(x, y, 0);
			biome = LevelSave.getLevelSaveBiome(save);
		}
		if (biome == null)
			biome = WorldGenerator.getBiome(x, y);
		 */
			Level level = biome.getNewLevel(x, y, dim, server, server.world.worldEntity);
			if (level == null)
				level = new Level(new LevelIdentifier(x, y, dim), 100, 100, server);
			level.biome = biome;
			return level;
		}
		//return level;
		return null;

		/*switch (dim) {
			case 20:
				return new SnowyCrystalMines(x, y, dim, server);
			case 21:
				return new DesertCrystalMines(x, y, dim, server);
			case 22:
				return new SwampCrystalMines(x, y, dim, server);
			case 23:
				return new ForestCrystalMines(x, y, dim, server);
			case 24: {
				return new PlainsCrystalMines(x, y, dim, server);
			}
		}
		return null;*/
	}
}

package com.jubiman.scourgemod.level.maps.biomes.CrystalMines;

import necesse.engine.GameEvents;
import necesse.engine.events.worldGeneration.GenerateCaveLayoutEvent;
import necesse.engine.events.worldGeneration.GeneratedCaveLayoutEvent;
import necesse.engine.util.LevelIdentifier;
import necesse.engine.world.WorldEntity;
import necesse.level.maps.Level;
import necesse.level.maps.generationModules.CaveGeneration;

public class SnowCrystalMinesCaveLevel extends Level {
	public SnowCrystalMinesCaveLevel(LevelIdentifier identifier, int width, int height, WorldEntity worldEntity) {
		super(identifier, width, height, worldEntity);
	}

	public SnowCrystalMinesCaveLevel(int islandX, int islandY, int dimension, WorldEntity worldEntity) {
		super(new LevelIdentifier(islandX, islandY, dimension), 300, 300, worldEntity);
		this.isCave = true;
		this.generateLevel();
	}

	public void generateLevel() {
		CaveGeneration cg = new CaveGeneration(this, "snowrocktile", "snowrock");
		GameEvents.triggerEvent(new GenerateCaveLayoutEvent(this, cg), (e) -> {
			cg.generateLevel();
		});
		GameEvents.triggerEvent(new GeneratedCaveLayoutEvent(this, cg));
		System.out.println("Generated Cave Level thing");
	}
}

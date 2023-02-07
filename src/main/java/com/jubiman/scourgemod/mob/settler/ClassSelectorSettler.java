package com.jubiman.scourgemod.mob.settler;

import com.jubiman.scourgemod.mob.human.ClassSelectorHumanMob;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.registries.MobRegistry;
import necesse.engine.util.TicketSystemList;
import necesse.entity.mobs.friendly.human.HumanMob;
import necesse.level.maps.Level;
import necesse.level.maps.levelData.settlementData.SettlementLevelData;
import necesse.level.maps.levelData.settlementData.settler.Settler;

import java.awt.*;
import java.util.function.Supplier;

public class ClassSelectorSettler extends Settler {
	public ClassSelectorSettler() {
		super("classselectorhuman");
	}

	public void addNewRecruitSettler(SettlementLevelData data, boolean isRandomEvent, TicketSystemList<Supplier<HumanMob>> ticketSystem) {
		ticketSystem.addObject(66, getNewRecruitMob(data));
	}

	@Override
	public void spawnAtClient(Server server, ServerClient client, Level level) {
		ClassSelectorHumanMob mob = (ClassSelectorHumanMob) MobRegistry.getMob(this.mobType, level);
		Point spawnPos = client.playerMob.getMapPos();
		if (spawnPos != null)
			level.entityManager.addMob(mob, spawnPos.x, spawnPos.y);
	}
}

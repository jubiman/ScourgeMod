package com.jubiman.scourgemod.patch;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.util.GameMath;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.ActiveBuff;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = Mob.class, name = "setManaHidden", arguments = {float.class})
public class ManaExhaustionPatch {
	@Advice.OnMethodExit
	static void onExit(@Advice.This Mob self) {
		int maxMana = self.getMaxMana();
		float stopExhaustionLimit = maxMana * 0.05f;
		if (self.getMana() <= stopExhaustionLimit) {
			self.isManaExhausted = true;
			self.buffManager.addBuff(new ActiveBuff(BuffRegistry.Debuffs.MANA_EXHAUSTION, self, 1000, null), false);
		}
	}
}

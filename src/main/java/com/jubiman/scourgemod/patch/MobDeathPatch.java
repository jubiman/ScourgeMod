package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.mob.ScourgeMob;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.util.Logger;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.friendly.critters.CritterMob;
import necesse.entity.mobs.friendly.human.HumanMob;
import necesse.entity.mobs.hostile.bosses.BossMob;
import necesse.entity.mobs.hostile.bosses.BossWormMobBody;
import necesse.entity.mobs.hostile.bosses.BossWormMobHead;
import necesse.entity.mobs.hostile.bosses.PestWardenHead;
import net.bytebuddy.asm.Advice;

import java.util.HashSet;

@ModMethodPatch(target = Mob.class, name = "onDeath", arguments = {Attacker.class, HashSet.class})
public class MobDeathPatch {
	public static void func(Mob mob, HashSet<Attacker> attackers) {
		HashSet<PlayerMob> players = attackers.stream().filter(a -> a.getAttackOwner() instanceof PlayerMob).map(a -> (PlayerMob) a.getAttackOwner()).collect(HashSet::new, HashSet::add, HashSet::addAll);
		for (PlayerMob playerMob : players) {
			ScourgePlayer player;
			if (!playerMob.isServerClient())
				return;

			player = ScourgePlayersHandler.getPlayer(playerMob);
			int exp;
			if (mob instanceof ScourgeMob)
				exp = ((ScourgeMob) mob).getExpGain();
			else { // Necesse mobs
				if (mob instanceof CritterMob) {
					exp = 1;
				} else if (mob instanceof BossMob || mob instanceof BossWormMobHead || mob instanceof BossWormMobBody) {
					exp = mob.getMaxHealth() / players.size() / 10;
				} else exp = mob.getMaxHealth();
			}
			if (player.addExp(exp, playerMob.getServerClient()))
				playerMob.getServerClient().sendChatMessage("You leveled up to level " + player.getLevel() + "!");
		}
	}
	@Advice.OnMethodExit
	static void onExit(@Advice.This Mob mob, @Advice.Argument(1) HashSet<Attacker> attackers) {
		func(mob, attackers);
	}
}
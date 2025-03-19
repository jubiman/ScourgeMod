package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.mob.ScourgeMob;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.friendly.critters.CritterMob;
import necesse.entity.mobs.friendly.human.HumanMob;
import necesse.entity.mobs.hostile.bosses.BossMob;
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
				switch (mob.getStringID()) {
					case "ancientarmoredskeleton": {
						exp = 1300;
						break;
					}
					case "ancientskeleton":
					case "ancientskeletonthrower":
					case "enchantedzombiearcher":
					case "voidwizard": {
						exp = 700;
						break;
					}
					case "ancientskeletonmage": {
						exp = 1100;
						break;
					}
					case "blackcavespider":
					case "enchantedzombie":
					case "enchantedcrawlingzombie":
					case "pirateparrot": {
						exp = 20;
						break;
					}
					case "cavemole":
					case "trapperzombie":
					case "zombie":
					case "zombiearcher": {
						exp = 10;
						break;
					}
					case "crawlingzombie":
					case "golbin": {
						exp = 5;
						break;
					}
					case "cryoflake":
					case "giantcavespider":
					case "mummy":
					case "mummymage":
					case "jackal":
					case "vampire":
					case "skeleton":
					case "skeletonminer":
					case "skeletonthrower": {
						exp = 50;
						break;
					}
					case "deepcavespirit":
					case "evilsprotector": {
						exp = 300;
						break;
					}
					case "desertcrawler":
					case "piraterecruit": {
						exp = 250;
						break;
					}
					case "frostsentry":
					case "frozendwarf":
					case "sandspirit":
					case "snowwolf":
					case "swampzombie": {
						exp = 100;
						break;
					}
					case "ninja":
					case "swampcavespider": {
						exp = 200;
						break;
					}
					case "swampshooter":
					case "voidapprentice": {
						exp = 75;
						break;
					}
					case "swampslime": {
						exp = 25;
						break;
					}
					case "queenspider": {
						exp = 500;
						break;
					}
					case "swampguardian": {
						exp = 750;
						break;
					}
					case "ancientvulture": {
						exp = 1500;
						break;
					}
					case "piratecaptain": {
						exp = 5000;
						break;
					}
					case "reaper": {
						exp = 6666;
						break;
					}
					case "cryoqueen": {
						exp = 7777;
						break;
					}
					case "pestwarden": {
						exp = 9889;
						break;
					}
					case "sage":
					case "grit": {
						exp = 10000;
						break;
					}
					case "fallenwizard": {
						exp = 11111;
						break;
					}
					default: {
						if (mob instanceof CritterMob) {
							exp = 1;
						} else if (mob instanceof HumanMob) {
							exp = 500;
						} else if (mob instanceof BossMob) {
							exp = mob.getMaxHealth() / 10; // 10% of mob's max health
						} else exp = mob.getMaxHealth();
						break;
					}
				}
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
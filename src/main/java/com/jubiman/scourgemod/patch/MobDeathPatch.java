package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.mob.ScourgeMob;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

import java.util.HashSet;

@ModMethodPatch(target = Mob.class, name = "onDeath", arguments = {Attacker.class, HashSet.class})
public class MobDeathPatch {
	@Advice.OnMethodExit
	static void onExit(@Advice.This Mob mob, @Advice.Argument(0) Attacker attacker, @Advice.Argument(1) HashSet<Attacker> attackers) {
		HashSet<Attacker> atks = new HashSet<>(attackers);
		if (attacker != null) {
			atks.clear();
			atks.add(attacker);
		}
		for (Attacker a : atks) {
			if (a.getAttackOwner() instanceof PlayerMob) {
				PlayerMob playerMob = (PlayerMob) a.getAttackOwner();
				ScourgePlayer player;
				if (!playerMob.isServerClient())
					return;

				player = ScourgePlayers.getPlayer(playerMob.getServerClient().authentication);
				int exp = 0;
				if (mob instanceof ScourgeMob)
					exp = ((ScourgeMob) mob).getExpGain();
				else { // Necesse mobs
					switch (mob.getStringID()) {
						case "ancientarmoredskeleton": {
							exp = 700;
							break;
						}
						case "ancientskeleton":
						case "ancientskeletonthrower":
						case "enchantedzombiearcher":
						case "mummymage":
						case "voidwizard": {
							exp = 500;
							break;
						}
						case "ancientskeletonmage": {
							exp = 600;
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
						case "crawlingzombie": {
							exp = 5;
							break;
						}
						case "cryoflake":
						case "giantcavespider":
						case "mummy":
						case "jackal":
						case "vampire": {
							exp = 15;
							break;
						}
						case "deepcavespirit":
						case "evilsprotector": {
							exp = 100;
							break;
						}
						case "desertcrawler":
						case "piraterecruit": {
							exp = 150;
							break;
						}
						case "frostsentry":
						case "frozendwarf":
						case "sandspirit":
						case "snowwolf":
						case "swampzombie": {
							exp = 50;
							break;
						}
						case "golbin": {
							exp = 4;
							break;
						}
						case "ninja":
						case "swampcavespider": {
							exp = 200;
							break;
						}
						case "skeleton":
						case "skeletonminer":
						case "skeletonthrower": {
							exp = 44;
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
							exp = 250;
							break;
						}
						case "swampguardian": {
							exp = 750;
							break;
						}
						case "ancientvulture": {
							exp = 1000;
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
					}
				}
				if (player.addExp(exp, playerMob.getServerClient()))
					playerMob.getServerClient().sendChatMessage("You leveled up to level " + player.getLevel() + "!");
			}
		}
	}
}
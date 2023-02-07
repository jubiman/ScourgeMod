package com.jubiman.scourgemod.mob.human;

import com.jubiman.scourgemod.mob.Textures;
import necesse.engine.network.packet.PacketOpenContainer;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.engine.registries.ContainerRegistry;
import necesse.engine.registries.MobRegistry;
import necesse.engine.tickManager.TickManager;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.MobDrawable;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.friendly.human.humanShop.HumanShop;
import necesse.entity.particle.FleshParticle;
import necesse.entity.particle.Particle;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.DrawOptions;
import necesse.gfx.drawOptions.human.HumanDrawOptions;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.inventory.InventoryItem;
import necesse.inventory.container.mob.ExplorerContainer;
import necesse.level.maps.Level;
import necesse.level.maps.light.GameLight;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ClassSelectorHumanMob extends HumanShop {
	public ClassSelectorHumanMob() {
		super(500, 200, "classselector");
		this.attackCooldown = 500;
		this.attackAnimSpeed = 500;
		this.setSwimSpeed(1.0F);
		this.collision = new Rectangle(-10, -7, 20, 14);
		this.hitBox = new Rectangle(-14, -12, 28, 24);
		this.selectBox = new Rectangle(-14, -41, 28, 48);
		// TODO: change?
		this.equipmentInventory.setItem(6, new InventoryItem("coppersword"));
	}

	@Override
	public PacketOpenContainer getOpenShopPacket(Server server, ServerClient client) {
		// TODO: change
		return PacketOpenContainer.Mob(ContainerRegistry.EXPLORER_CONTAINER, this, ExplorerContainer.getExplorerContainerContent(this, server, client, this.getShopItemsContentPacket(client)));
	}

	@Override
	public HumanGender getHumanGender() {
		return HumanGender.MALE;
	}

	@Override
	public DrawOptions getUserDrawOptions(Level level, int x, int y, TickManager tickManager, GameCamera camera, PlayerMob perspective, Consumer<HumanDrawOptions> humanDrawOptionsModifier) {
		GameLight light = level.getLightLevel(x / 32, y / 32);
		int drawX = camera.getDrawX(x) - 22 - 10;
		int drawY = camera.getDrawY(y) - 44 - 7;
		Point sprite = this.getAnimSprite(x, y, this.dir);
		HumanDrawOptions humanOptions = (new HumanDrawOptions(MobRegistry.Textures.animalKeeper)).helmet(this.getDisplayArmor(0, (String)null)).chestplate(this.getDisplayArmor(1, "animalkeepershirt")).boots(this.getDisplayArmor(2, "animalkeepershoes")).sprite(sprite).dir(this.dir).light(light);
		humanDrawOptionsModifier.accept(humanOptions);
		DrawOptions drawOptions = humanOptions.pos(drawX, drawY);
		DrawOptions markerOptions = this.getMarkerDrawOptions(x, y, light, camera, 0, -45, perspective);
		return () -> {
			drawOptions.draw();
			markerOptions.draw();
		};
	}

	@Override
	public List<InventoryItem> getRecruitItems(ServerClient client) {
		// TODO
		if (this.isSettler()) {
			return null;
		} else {
			GameRandom random = new GameRandom(this.getSettlerSeed() * 83L);
			if (this.isTravelingHuman()) {
				return Collections.singletonList(new InventoryItem("coin", random.getIntBetween(100, 1000)));
			} else {
				ArrayList<InventoryItem> out = new ArrayList<>();
				out.add(new InventoryItem("coin", random.getIntBetween(100, 1000)));
				return out;
			}
		}
	}

	public void spawnDeathParticles(float knockbackX, float knockbackY) {
		for(int i = 0; i < 4; ++i) {
			this.getLevel().entityManager.addParticle(new FleshParticle(this.getLevel(), MobRegistry.Textures.animalKeeper.body, GameRandom.globalRandom.nextInt(5), 8, 32, this.x, this.y, 10.0F, knockbackX, knockbackY), Particle.GType.IMPORTANT_COSMETIC);
		}
	}

	@Override
	public void addDrawables(List<MobDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, Level level, int x, int y, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
		super.addDrawables(list, tileList, topList, level, x, y, tickManager, camera, perspective);
		if (this.objectUser == null || this.objectUser.object.drawsUsers()) {
			if (this.isVisible()) {
				GameLight light = level.getLightLevel(x / 32, y / 32);
				int drawX = camera.getDrawX(x) - 22 - 10;
				int drawY = camera.getDrawY(y) - 44 - 7;
				Point sprite = this.getAnimSprite(x, y, this.dir);
				drawY += this.getBobbing(x, y);
				drawY += this.getLevel().getTile(x / 32, y / 32).getMobSinkingAmount(this);
				HumanDrawOptions humanDrawOptions = (new HumanDrawOptions(MobRegistry.Textures.animalKeeper)).helmet(this.getDisplayArmor(0, (String)null)).chestplate(this.getDisplayArmor(1, "animalkeepershirt")).boots(this.getDisplayArmor(2, "animalkeepershoes")).sprite(sprite).dir(this.dir).light(light);
				if (this.inLiquid(x, y)) {
					drawY -= 10;
					humanDrawOptions.armSprite(2);
					humanDrawOptions.mask(MobRegistry.Textures.boat_mask[sprite.y % 4], 0, -7);
				}

				humanDrawOptions = this.setCustomItemAttackOptions(humanDrawOptions);
				final DrawOptions drawOptions = humanDrawOptions.pos(drawX, drawY);
				final DrawOptions boat = this.inLiquid(x, y) ? MobRegistry.Textures.woodBoat.initDraw().sprite(0, this.dir % 4, 64).light(light).pos(drawX, drawY + 7) : null;
				final DrawOptions markerOptions = this.getMarkerDrawOptions(x, y, light, camera, 0, -45, perspective);
				list.add(new MobDrawable() {
					public void draw(TickManager tickManager) {
						if (boat != null) {
							boat.draw();
						}

						drawOptions.draw();
						markerOptions.draw();
					}
				});
				this.addShadowDrawables(tileList, x, y, light, camera);
			}
		}
	}
}

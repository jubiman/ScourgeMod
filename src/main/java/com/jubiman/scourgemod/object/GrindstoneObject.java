package com.jubiman.scourgemod.object;

import com.jubiman.scourgemod.container.GrindstoneContainer;
import com.jubiman.scourgemod.registry.ModContainerRegistry;
import necesse.engine.network.packet.PacketOpenContainer;
import necesse.engine.registries.ContainerRegistry;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.objectEntity.ObjectEntity;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.texture.TextureDrawOptions;
import necesse.gfx.drawables.LevelSortedDrawable;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.container.object.CraftingStationContainer;
import necesse.inventory.item.toolItem.ToolType;
import necesse.level.gameObject.GameObject;
import necesse.level.gameObject.ObjectHoverHitbox;
import necesse.level.maps.Level;
import necesse.level.maps.light.GameLight;

import java.awt.*;
import java.util.List;

public class GrindstoneObject extends GameObject {
	private GameTexture texture;

	public GrindstoneObject() {
		super(new Rectangle(0, 1, 32, 30)); // Collision relative to the tile it's placed on
		// Remember that tiles are 32x32 pixels in size
		hoverHitbox = new Rectangle(0, 0, 32, 32); // 2 tiles high mouse hover hitbox
		toolType = ToolType.PICKAXE; // Can be broken by all tools
		isLightTransparent = true; // Let's light pass through
		mapColor = new Color(31, 150, 148); // Also applies as debris color if not set
	}


	@Override
	public void loadTextures() {
		texture = GameTexture.fromFile("objects/scourge_grindstone");
	}

	@Override
	public void addDrawables(List<LevelSortedDrawable> list, OrderableDrawables tileList, Level level, int tileX, int tileY, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
		GameLight light = level.getLightLevel(tileX, tileY);
		int drawX = camera.getTileDrawX(tileX);
		int drawY = camera.getTileDrawY(tileY);
		TextureDrawOptions options = texture.initDraw().light(light).pos(drawX, drawY);
		// Can choose sprite with texture.initDraw().sprite(...)

		list.add(new LevelSortedDrawable(this, tileX, tileY) {
			@Override
			public int getSortY() {
				// Basically where this will be sorted on the Y axis (when it will be behind the player etc.)
				// Should be in [0 - 32] range
				return 16;
			}

			@Override
			public void draw(TickManager tickManager) {
				options.draw();
			}
		});
	}

	@Override
	public void drawPreview(Level level, int tileX, int tileY, int rotation, float alpha, PlayerMob player, GameCamera camera) {
		int drawX = camera.getTileDrawX(tileX);
		int drawY = camera.getTileDrawY(tileY);
		texture.initDraw().alpha(alpha).draw(drawX, drawY);
	}


	@Override
	public ObjectEntity getNewObjectEntity(Level level, int x, int y) {
		// TODO: create object entity
		return null;
	}

	@Override
	public List<ObjectHoverHitbox> getHoverHitboxes(Level level, int tileX, int tileY) {
		List<ObjectHoverHitbox> list = super.getHoverHitboxes(level, tileX, tileY);
		list.add(new ObjectHoverHitbox(tileX, tileY, 0, -10, 32, 10));
		return list;
	}

	@Override
	public boolean canInteract(Level level, int x, int y, PlayerMob player) {
		return true;
	}

	@Override
	public void interact(Level level, int x, int y, PlayerMob player) {
		if (level.isServerLevel()) {
			ContainerRegistry.openAndSendContainer(player.getServerClient(),
					new PacketOpenContainer(ModContainerRegistry.GRINDSTONE_CONTAINER,
							GrindstoneContainer.getContainerContent(player.getServerClient())
					)
			);
		}
	}
}

package com.jubiman.scourgemod.registry;

import com.jubiman.scourgemod.container.GrindstoneContainer;
import com.jubiman.scourgemod.form.GrindstoneContainerForm;
import necesse.engine.registries.ContainerRegistry;

public class ModContainerRegistry {
	public static final int GRINDSTONE_CONTAINER;

	static {
		GRINDSTONE_CONTAINER = ContainerRegistry.registerContainer(
				(client, uniqueSeed, content) -> new GrindstoneContainerForm(client,
						new GrindstoneContainer(client.getClient(), uniqueSeed, content)),
				(client, uniqueSeed, content, serverObject) -> new GrindstoneContainer(client, uniqueSeed, content)
		);

	}
}

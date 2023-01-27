package eliasstar.hexhex;

import eliasstar.hexhex.feature.Feature;
import eliasstar.hexhex.feature.Features;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientInitializer implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		Features.ALL.forEach(Feature::queryMinecraftClient);

		Features.ALL.stream()
				.filter(Feature.StartTickable.class::isInstance)
				.map(Feature.StartTickable.class::cast)
				.forEach(ClientTickEvents.START_CLIENT_TICK::register);

		Features.ALL.stream()
				.filter(Feature.EndTickable.class::isInstance)
				.map(Feature.EndTickable.class::cast)
				.forEach(ClientTickEvents.END_CLIENT_TICK::register);
	}

}

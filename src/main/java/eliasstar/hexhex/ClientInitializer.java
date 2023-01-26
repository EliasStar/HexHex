package eliasstar.hexhex;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eliasstar.hexhex.feature.Feature;
import eliasstar.hexhex.feature.creativeflight.CreativeFlight;
import eliasstar.hexhex.feature.entityflight.EntityFlight;
import eliasstar.hexhex.feature.nightvision.NightVision;
import eliasstar.hexhex.feature.nofall.NoFall;
import eliasstar.hexhex.feature.xray.XRay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientInitializer implements ClientModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("minceraft-hex");
	public static final List<Feature> FEATURES = List.of(
			CreativeFlight.INSTANCE,
			EntityFlight.INSTANCE,
			NightVision.INSTANCE,
			NoFall.INSTANCE,
			XRay.INSTANCE);

	@Override
	public void onInitializeClient() {
		FEATURES.stream()
				.filter(Feature.StartTickable.class::isInstance)
				.map(Feature.StartTickable.class::cast)
				.forEach(ClientTickEvents.START_CLIENT_TICK::register);

		FEATURES.stream()
				.filter(Feature.EndTickable.class::isInstance)
				.map(Feature.EndTickable.class::cast)
				.forEach(ClientTickEvents.END_CLIENT_TICK::register);
	}

}

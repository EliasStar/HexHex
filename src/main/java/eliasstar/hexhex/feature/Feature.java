package eliasstar.hexhex.feature;

import java.util.Optional;

import eliasstar.hexhex.gui.base.BaseScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class Feature {

    protected final String name;

    protected boolean enabled;
    protected Optional<Minecraft> client;

    protected Feature(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
        this.client = Optional.ofNullable(Minecraft.getInstance());
    }

    public void queryMinecraftClient() {
        client = Optional.ofNullable(Minecraft.getInstance());
    }

    public void toggle() {
        enabled = !enabled;
    }

    public boolean enabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return name;
    }

    @FunctionalInterface
    public interface EndTickable extends ClientTickEvents.EndTick {
    }

    @FunctionalInterface
    public interface StartTickable extends ClientTickEvents.StartTick {
    }

    @FunctionalInterface
    public interface WithConfigScreen {
        public BaseScreen getConfigScreen();
    }
}

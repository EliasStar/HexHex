package eliasstar.hexhex.feature;

import eliasstar.hexhex.gui.base.BaseScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class Feature {

    protected final Minecraft client;
    protected final String name;

    protected boolean enabled;

    protected Feature(String name, boolean enabled) {
        this.client = Minecraft.getInstance();
        this.name = name;
        this.enabled = enabled;
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

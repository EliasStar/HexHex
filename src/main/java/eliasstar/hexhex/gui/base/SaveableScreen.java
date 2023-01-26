package eliasstar.hexhex.gui.base;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class SaveableScreen extends BaseScreen {

    protected SaveableScreen(Component title, Screen parent) {
        super(title, parent);
    }

    @Override
    protected void init() {
        super.init();

        addRenderableWidget(new Button(xEnd - 85, yStart + 4, 40, 20, Component.literal("Save"), button -> {
            save();
            minecraft.setScreen(parent);
        }));
    }

    protected abstract void save();
}

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

        addRenderableWidget(Button.builder(Component.literal("Save"), b -> {
            save();
            minecraft.setScreen(parent);
        })
                .pos(xEnd - 85, yStart + 4)
                .width(40)
                .build());
    }

    protected abstract void save();
}

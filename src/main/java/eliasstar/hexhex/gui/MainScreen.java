package eliasstar.hexhex.gui;

import eliasstar.hexhex.ClientInitializer;
import eliasstar.hexhex.feature.Feature;
import eliasstar.hexhex.gui.base.BaseScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class MainScreen extends BaseScreen {

    public MainScreen(Screen parent) {
        super(Component.literal("HexHex"), parent);
    }

    @Override
    protected void init() {
        super.init();

        var row = 0;
        for (var feature : ClientInitializer.FEATURES) {
            var y = yStart + 35 + 25 * row++;

            addRenderableWidget(new Button(xStart + 10, y, 178, 20, enabledText(feature), button -> {
                feature.toggle();
                button.setMessage(enabledText(feature));
            }));

            if (feature instanceof Feature.WithConfigScreen featureWithConfig) {
                var configScreen = featureWithConfig.getConfigScreen();
                configScreen.setParent(this);

                addRenderableWidget(new Button(xEnd - 60, y, 50, 20, Component.literal("Config"),
                        button -> minecraft.setScreen(configScreen)));
            }
        }
    }

    private Component enabledText(Feature feature) {
        return Component.literal((feature.enabled() ? "Disable " : "Enable ") + feature.toString());
    }

}

package eliasstar.hexhex.gui;

import eliasstar.hexhex.feature.Feature;
import eliasstar.hexhex.feature.Features;
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
        for (var feature : Features.ALL) {
            var y = yStart + 35 + 25 * row++;

            addRenderableWidget(Button.builder(enabledText(feature), button -> {
                feature.toggle();
                button.setMessage(enabledText(feature));
            })
                    .pos(xStart + 10, y)
                    .width(178)
                    .build());

            if (feature instanceof Feature.WithConfigScreen featureWithConfig) {
                var configScreen = featureWithConfig.getConfigScreen();
                configScreen.setParent(this);

                addRenderableWidget(
                        Button.builder(Component.literal("Config"), button -> minecraft.setScreen(configScreen))
                                .pos(xEnd - 60, y)
                                .width(50)
                                .build());
            }
        }
    }

    private Component enabledText(Feature feature) {
        return Component.literal((feature.enabled() ? "Disable " : "Enable ") + feature.toString());
    }

}

package eliasstar.hexhex.feature.nofall;

import com.mojang.blaze3d.vertex.PoseStack;
import eliasstar.hexhex.gui.base.SaveableScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class NoFallConfigScreen extends SaveableScreen {

    private EditBox maxFallDistanceWidget;

    protected NoFallConfigScreen() {
        super(Component.literal("NoFall Config"), null);
    }

    @Override
    protected void init() {
        super.init();

        maxFallDistanceWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 35, 30, 18, Component.empty()));
        maxFallDistanceWidget.setValue(Double.toString(NoFall.INSTANCE.getMaxFallDistance()));
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        font.draw(matrices, "Max Fall Distance (Blocks):", xStart + 10, yStart + 40, 0x404040);
    }

    @Override
    protected void save() {
        try {
            var maxFallDistance = Double.parseDouble(maxFallDistanceWidget.getValue());

            NoFall.INSTANCE.setMaxFallDistance(maxFallDistance);
        } catch (NumberFormatException e) {
        }
    }

}

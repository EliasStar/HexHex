package eliasstar.hexhex.feature.creativeflight;

import com.mojang.blaze3d.vertex.PoseStack;
import eliasstar.hexhex.gui.base.SaveableScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class CreativeFlightConfigScreen extends SaveableScreen {

    private EditBox fallDistanceWidget;
    private EditBox fallIntervalWidget;

    protected CreativeFlightConfigScreen() {
        super(Component.literal("CreativeFlight Config"), null);
    }

    @Override
    protected void init() {
        super.init();

        fallDistanceWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 35, 30, 18, Component.empty()));
        fallDistanceWidget.setValue(Double.toString(CreativeFlight.INSTANCE.getFallDistance()));

        fallIntervalWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 65, 30, 18, Component.empty()));
        fallIntervalWidget.setValue(Integer.toString(CreativeFlight.INSTANCE.getFallInterval()));
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        font.draw(matrices, "Fall Distance (Blocks):", xStart + 10, yStart + 40, 0x404040);
        font.draw(matrices, "Fall Interval (Ticks):", xStart + 10, yStart + 70, 0x404040);
    }

    @Override
    protected void save() {
        try {
            var fallDistance = Double.parseDouble(fallDistanceWidget.getValue());
            var fallInterval = Integer.parseInt(fallIntervalWidget.getValue());

            CreativeFlight.INSTANCE.setFallDistance(fallDistance);
            CreativeFlight.INSTANCE.setFallInterval(fallInterval);
        } catch (NumberFormatException e) {
        }
    }

}

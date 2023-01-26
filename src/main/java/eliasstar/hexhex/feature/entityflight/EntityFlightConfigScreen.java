package eliasstar.hexhex.feature.entityflight;

import com.mojang.blaze3d.vertex.PoseStack;
import eliasstar.hexhex.gui.base.SaveableScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class EntityFlightConfigScreen extends SaveableScreen {

    private EditBox horizontalVelocityWidget;
    private EditBox verticalVelocityWidget;
    private EditBox fallIntervalWidget;

    protected EntityFlightConfigScreen() {
        super(Component.literal("EntityFlight Config"), null);
    }

    @Override
    protected void init() {
        super.init();

        horizontalVelocityWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 35, 30, 18, Component.empty()));
        horizontalVelocityWidget.setValue(Double.toString(EntityFlight.INSTANCE.getHorizontalVelocity()));

        verticalVelocityWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 65, 30, 18, Component.empty()));
        verticalVelocityWidget.setValue(Double.toString(EntityFlight.INSTANCE.getVerticalVelocity()));

        fallIntervalWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 95, 30, 18, Component.empty()));
        fallIntervalWidget.setValue(Integer.toString(EntityFlight.INSTANCE.getFallInterval()));
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        font.draw(matrices, "Horizontal Velocity (Blocks/Seconds):", xStart + 10, yStart + 40, 0x404040);
        font.draw(matrices, "Vertical Velocity (Blocks/Seconds):", xStart + 10, yStart + 70, 0x404040);
        font.draw(matrices, "Fall Interval (Ticks):", xStart + 10, yStart + 100, 0x404040);
    }

    @Override
    protected void save() {
        try {
            var horizontalVelocity = Double.parseDouble(horizontalVelocityWidget.getValue());
            var verticalVelocity = Double.parseDouble(verticalVelocityWidget.getValue());
            var fallInterval = Integer.parseInt(fallIntervalWidget.getValue());

            EntityFlight.INSTANCE.setHorizontalVelocity(horizontalVelocity);
            EntityFlight.INSTANCE.setVerticalVelocity(verticalVelocity);
            EntityFlight.INSTANCE.setFallInterval(fallInterval);
        } catch (NumberFormatException e) {
        }
    }

}

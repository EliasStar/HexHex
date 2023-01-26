package eliasstar.hexhex.feature.nightvision;

import com.mojang.blaze3d.vertex.PoseStack;
import eliasstar.hexhex.gui.base.SaveableScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class NightVisionConfigScreen extends SaveableScreen {

    private EditBox brightnessWidget;

    protected NightVisionConfigScreen() {
        super(Component.literal("NightVision Config"), null);
    }

    @Override
    protected void init() {
        super.init();

        brightnessWidget = addRenderableWidget(
                new EditBox(font, xEnd - 40, yStart + 35, 30, 18, Component.empty()));
        brightnessWidget.setValue(Double.toString(NightVision.INSTANCE.getBrightness()));
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        font.draw(matrices, "Brightness (Gamma):", xStart + 10, yStart + 40, 0x404040);
    }

    @Override
    protected void save() {
        try {
            var brightness = Double.parseDouble(brightnessWidget.getValue());

            NightVision.INSTANCE.setBrightness(brightness);
        } catch (NumberFormatException e) {
        }
    }

}

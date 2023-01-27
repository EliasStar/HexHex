package eliasstar.hexhex.feature.xray;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import eliasstar.hexhex.gui.base.BaseScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class XRayConfigScreen extends BaseScreen {

    private EditBox blockWidget;
    private List<Button> removeBlockWidgets;

    protected XRayConfigScreen() {
        super(Component.literal("X-Ray Config"), null);
        removeBlockWidgets = new ArrayList<>();
    }

    @Override
    protected void init() {
        super.init();

        blockWidget = addRenderableWidget(
                new EditBox(font, xStart + 10, yStart + 41, 192, 18, Component.empty()));

        addRenderableWidget(Button.builder(Component.literal("Add"), button -> {
            if (XRay.INSTANCE.addBlock(blockWidget.getValue())) {
                blockWidget.setValue("");
                updateRemoveEffectWidgets();
            }
        })
                .pos(xEnd - 45, yStart + 40)
                .width(35)
                .build());

        updateRemoveEffectWidgets();
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);

        font.draw(matrices, "Block (Identifier):", xStart + 10, yStart + 30, 0x404040);
    }

    private void updateRemoveEffectWidgets() {
        removeBlockWidgets.forEach(this::removeWidget);
        removeBlockWidgets.clear();

        var counter = 0;
        for (var block : XRay.INSTANCE.getBlocks()) {
            var x = counter % 2 == 0 ? xStart + 10 : xEnd - 120;
            var y = yStart + 65 + 25 * (counter / 2);

            removeBlockWidgets.add(addRenderableWidget(Button.builder(block.getName(), button -> {
                XRay.INSTANCE.removeBlock(block);
                updateRemoveEffectWidgets();
            })
                    .pos(x, y)
                    .width(110)
                    .build()));

            counter++;
        }
    }
}

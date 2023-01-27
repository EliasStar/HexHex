package eliasstar.hexhex.gui.base;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class BaseScreen extends Screen {

    protected static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.tryParse("textures/gui/demo_background.png");
    protected static final int BACKGROUND_WIDTH = 248;
    protected static final int BACKGROUND_HEIGHT = 166;

    protected Screen parent;

    protected int xStart;
    protected int xEnd;

    protected int yStart;
    protected int yEnd;

    protected BaseScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    public Screen getParent() {
        return parent;
    }

    public void setParent(Screen parent) {
        this.parent = parent;
    }

    @Override
    protected void init() {
        xStart = (width - BACKGROUND_WIDTH) / 2;
        yStart = (height - BACKGROUND_HEIGHT) / 2;

        xEnd = xStart + BACKGROUND_WIDTH;
        yEnd = yStart + BACKGROUND_HEIGHT;

        addRenderableWidget(Button.builder(Component.literal("Back"), b -> minecraft.setScreen(parent))
                .pos(xEnd - 45, yStart + 4)
                .width(40)
                .build());
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);

        blit(matrices, xStart, yStart, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        hLine(matrices, xStart + 3, xEnd - 4, yStart + 25, 0xff000000);

        font.draw(matrices, title, xStart + 10, yStart + 10, 0x404040);
        super.render(matrices, mouseX, mouseY, delta);
    }

}

package eliasstar.hexhex.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eliasstar.hexhex.gui.MainScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends EffectRenderingInventoryScreen<InventoryMenu>
        implements RecipeUpdateListener {

    private InventoryScreenMixin() {
        super(null, null, null);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addMainScreenButton(CallbackInfo info) {
        addRenderableWidget(new ImageButton(this.leftPos + 77, this.topPos + 44, 16, 16, 0, 0, 0,
                ResourceLocation.tryParse("textures/item/fire_charge.png"), 16, 16,
                button -> minecraft.setScreen(new MainScreen(this))));
    }
}

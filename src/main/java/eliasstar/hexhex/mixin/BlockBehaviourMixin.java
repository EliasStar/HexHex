package eliasstar.hexhex.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eliasstar.hexhex.feature.xray.XRay;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {

    @Inject(method = "getShadeBrightness", at = @At("HEAD"), cancellable = true)
    private void xRaySetWhitelistedBlocksBright(BlockState state, BlockGetter level, BlockPos pos,
            CallbackInfoReturnable<Float> info) {
        if (XRay.INSTANCE.enabled())
            info.setReturnValue(1f);
    }

    @Inject(method = "getRenderShape", at = @At("HEAD"), cancellable = true)
    private void xRaySetBlacklistedBlocksInvisible(BlockState state, CallbackInfoReturnable<RenderShape> info) {
        if (XRay.INSTANCE.enabled() && !XRay.INSTANCE.isWhitelisted(state.getBlock()))
            info.setReturnValue(RenderShape.INVISIBLE);
    }
}

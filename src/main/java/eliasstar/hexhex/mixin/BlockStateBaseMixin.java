package eliasstar.hexhex.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eliasstar.hexhex.feature.xray.XRay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;

@Mixin(BlockStateBase.class)
public abstract class BlockStateBaseMixin extends StateHolder<Block, BlockState> {

    private BlockStateBaseMixin() {
        super(null, null, null);
    }

    @Shadow
    public abstract Block getBlock();

    @Inject(method = "canOcclude", at = @At("HEAD"), cancellable = true)
    private void xRaySetBlacklistedBlocksTransparent(CallbackInfoReturnable<Boolean> info) {
        if (XRay.INSTANCE.enabled() && !XRay.INSTANCE.isWhitelisted(getBlock()))
            info.setReturnValue(false);
    }

}

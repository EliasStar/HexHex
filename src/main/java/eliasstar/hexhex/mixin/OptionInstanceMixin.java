package eliasstar.hexhex.mixin;

import java.util.function.Consumer;
import net.minecraft.client.OptionInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import eliasstar.hexhex.mixin_interfaces.ForceableOptionInstance;

@Mixin(OptionInstance.class)
public abstract class OptionInstanceMixin<T> implements ForceableOptionInstance<T> {

    @Shadow
    T value;

    @Shadow
    public abstract T get();

    @Shadow
    public abstract void set(T value);

    @Shadow
    @Final
    private Consumer<T> onValueUpdate;

    @Override
    public void forceSet(T val) {
        value = val;
        onValueUpdate.accept(value);
    }

}

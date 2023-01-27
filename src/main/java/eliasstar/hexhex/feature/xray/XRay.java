package eliasstar.hexhex.feature.xray;

import java.util.HashSet;
import java.util.Set;

import eliasstar.hexhex.feature.Feature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class XRay extends Feature implements Feature.WithConfigScreen {

    public static final XRay INSTANCE = new XRay();

    private Set<Block> blocks = new HashSet<>();

    private XRay() {
        super("X-Ray", false);
    }

    @SuppressWarnings("resource")
    public boolean addBlock(String identifier) {
        var id = ResourceLocation.tryParse(identifier);
        if (!BuiltInRegistries.BLOCK.containsKey(id))
            return false;

        blocks.add(BuiltInRegistries.BLOCK.get(id));

        if (enabled && client.isPresent())
            client.get().levelRenderer.allChanged();

        return true;
    }

    @SuppressWarnings("resource")
    public void removeBlock(Block block) {
        blocks.remove(block);

        if (enabled && client.isPresent())
            client.get().levelRenderer.allChanged();
    }

    public boolean isWhitelisted(Block block) {
        return blocks.contains(block);
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    @Override
    @SuppressWarnings("resource")
    public void toggle() {
        if (!client.isPresent())
            return;

        super.toggle();

        client.get().smartCull = !enabled;
        client.get().levelRenderer.allChanged();
    }

    @Override
    public XRayConfigScreen getConfigScreen() {
        return new XRayConfigScreen();
    }

}

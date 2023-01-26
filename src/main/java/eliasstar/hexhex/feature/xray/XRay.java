package eliasstar.hexhex.feature.xray;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import eliasstar.hexhex.feature.Feature;

public class XRay extends Feature implements Feature.WithConfigScreen {

    public static final XRay INSTANCE = new XRay();

    private Set<Block> blocks = new HashSet<>();

    private XRay() {
        super("X-Ray", false);
    }

    public boolean addBlock(String identifier) {
        var id = ResourceLocation.tryParse(identifier);
        if (!Registry.BLOCK.containsKey(id))
            return false;

        blocks.add(Registry.BLOCK.get(id));

        if (enabled)
            client.levelRenderer.allChanged();

        return true;
    }

    public void removeBlock(Block block) {
        blocks.remove(block);

        if (enabled)
            client.levelRenderer.allChanged();
    }

    public boolean isWhitelisted(Block block) {
        return blocks.contains(block);
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void toggle() {
        super.toggle();

        client.smartCull = !enabled;
        client.levelRenderer.allChanged();
    }

    @Override
    public XRayConfigScreen getConfigScreen() {
        return new XRayConfigScreen();
    }

}

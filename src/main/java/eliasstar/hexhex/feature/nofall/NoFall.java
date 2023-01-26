package eliasstar.hexhex.feature.nofall;

import eliasstar.hexhex.feature.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class NoFall extends Feature implements Feature.EndTickable, Feature.WithConfigScreen {

    public static final NoFall INSTANCE = new NoFall();

    private double maxFallDistance = 2;

    private NoFall() {
        super("NoFall", false);
    }

    public double getMaxFallDistance() {
        return maxFallDistance;
    }

    public void setMaxFallDistance(double blocks) {
        maxFallDistance = blocks;
    }

    @Override
    public void onEndTick(Minecraft client) {
        if (!enabled || client.player == null || client.player.fallDistance < maxFallDistance
                || client.player.isFallFlying())
            return;

        client.player.connection.send(new ServerboundMovePlayerPacket.StatusOnly(true));
    }

    @Override
    public NoFallConfigScreen getConfigScreen() {
        return new NoFallConfigScreen();
    }

}

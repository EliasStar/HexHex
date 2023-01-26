package eliasstar.hexhex.feature.creativeflight;

import eliasstar.hexhex.feature.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class CreativeFlight extends Feature implements Feature.EndTickable, Feature.WithConfigScreen {

    public static final CreativeFlight INSTANCE = new CreativeFlight();

    private double fallDistance = 1.5;
    private int fallInterval = 20;

    private int counter;

    private CreativeFlight() {
        super("CreativeFlight", false);
    }

    public double getFallDistance() {
        return fallDistance;
    }

    public void setFallDistance(double blocks) {
        fallDistance = blocks;
    }

    public int getFallInterval() {
        return fallInterval;
    }

    public void setFallInterval(int ticks) {
        fallInterval = ticks;
    }

    @Override
    public void onEndTick(Minecraft client) {
        if (client.player == null)
            return;

        var abilities = client.player.getAbilities();
        if (!abilities.instabuild)
            abilities.mayfly = enabled;

        if (!abilities.flying)
            return;

        counter++;

        if (counter >= fallInterval) {
            counter = 0;

            var pos = client.player.position();
            var packet = new ServerboundMovePlayerPacket.Pos(pos.x, pos.y - fallDistance, pos.z, false);
            client.player.connection.send(packet);
        }
    }

    @Override
    public CreativeFlightConfigScreen getConfigScreen() {
        return new CreativeFlightConfigScreen();
    }

}

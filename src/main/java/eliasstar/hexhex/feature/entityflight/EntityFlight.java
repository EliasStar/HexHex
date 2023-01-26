package eliasstar.hexhex.feature.entityflight;

import eliasstar.hexhex.feature.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

public class EntityFlight extends Feature implements Feature.EndTickable, Feature.WithConfigScreen {

    public static final EntityFlight INSTANCE = new EntityFlight();

    private double horizontalVelocity = 1;
    private double verticalVelocity = 0.3;
    private int fallInterval = 20;

    private int counter;

    private EntityFlight() {
        super("EntityFlight", false);
    }

    public double getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(double blocksPerSec) {
        horizontalVelocity = blocksPerSec;
    }

    public double getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(double blocksPerSec) {
        verticalVelocity = blocksPerSec;
    }

    public int getFallInterval() {
        return fallInterval;
    }

    public void setFallInterval(int ticks) {
        fallInterval = ticks;
    }

    @Override
    public void onEndTick(Minecraft client) {
        if (!enabled || client.player == null || !client.player.isPassenger())
            return;

        var entity = client.player.getVehicle();
        entity.setYRot(client.player.getYRot());

        var yaw = entity.getYRot() * Math.PI / -180;
        var lookDirection = new Vec3(Math.sin(yaw), 0, Math.cos(yaw));

        var velocity = entity.getDeltaMovement();
        velocity = new Vec3(velocity.x, -verticalVelocity, velocity.z);

        if (client.options.keyUp.isDown()) {
            velocity = lookDirection.scale(horizontalVelocity).subtract(0, verticalVelocity, 0);
        }

        if (client.options.keyDown.isDown()) {
            velocity = lookDirection.scale(-horizontalVelocity).subtract(0, verticalVelocity, 0);
        }

        if (client.options.keyJump.isDown() && counter != 0) {
            velocity = new Vec3(velocity.x, verticalVelocity, velocity.z);
        }

        entity.setDeltaMovement(velocity);

        counter = (counter + 1) % fallInterval;
    }

    @Override
    public EntityFlightConfigScreen getConfigScreen() {
        return new EntityFlightConfigScreen();
    }

}

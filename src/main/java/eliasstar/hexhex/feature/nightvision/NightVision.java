package eliasstar.hexhex.feature.nightvision;

import eliasstar.hexhex.feature.Feature;
import eliasstar.hexhex.mixin_interfaces.ForceableSimpleOption;

public class NightVision extends Feature implements Feature.WithConfigScreen {

    public static final NightVision INSTANCE = new NightVision();

    private double brightness = 10;

    private double oldGamma;

    private NightVision() {
        super("NightVision", false);
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double gamma) {
        brightness = gamma;
    }

    @Override
    public void toggle() {
        super.toggle();

        @SuppressWarnings("unchecked")
        var gamma = (ForceableSimpleOption<Double>) (Object) client.options.gamma();

        if (enabled) {
            oldGamma = gamma.get();
            gamma.forceSet(brightness);
        } else {
            gamma.set(oldGamma);
        }
    }

    @Override
    public NightVisionConfigScreen getConfigScreen() {
        return new NightVisionConfigScreen();
    }

}

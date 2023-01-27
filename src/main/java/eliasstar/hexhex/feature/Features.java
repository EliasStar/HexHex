package eliasstar.hexhex.feature;

import java.util.List;

import eliasstar.hexhex.feature.creativeflight.CreativeFlight;
import eliasstar.hexhex.feature.entityflight.EntityFlight;
import eliasstar.hexhex.feature.nightvision.NightVision;
import eliasstar.hexhex.feature.nofall.NoFall;
import eliasstar.hexhex.feature.xray.XRay;

public class Features {

    public static final List<Feature> ALL = List.of(
        CreativeFlight.INSTANCE,
        EntityFlight.INSTANCE,
        NightVision.INSTANCE,
        NoFall.INSTANCE,
        XRay.INSTANCE);

}

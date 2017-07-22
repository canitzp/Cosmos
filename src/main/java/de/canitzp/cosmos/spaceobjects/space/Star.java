package de.canitzp.cosmos.spaceobjects.space;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.Util;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * @author canitzp
 */
public class Star extends SpaceObject<StarSystem> {

    public static final Star SUN = new Star(new ResourceLocation(Cosmos.MODID, "sun"), StarSystem.SOLAR_SYSTEM, 0, EnumFacing.NORTH);

    public Star(ResourceLocation name, StarSystem starSystem, double distanceToSunLy, EnumFacing directionToSun) {
        super(new ResourceLocation(name.getResourceDomain(), "star:" + name.getResourcePath()), starSystem, distanceToSunLy, directionToSun);
    }

}

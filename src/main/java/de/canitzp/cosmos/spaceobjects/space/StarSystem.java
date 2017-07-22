package de.canitzp.cosmos.spaceobjects.space;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.ctpcore.registry.IRegistryEntry;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * @author canitzp
 */
public class StarSystem extends SpaceObject<Galaxy> {

    public static final StarSystem SOLAR_SYSTEM = new StarSystem(new ResourceLocation(Cosmos.MODID, "solar_system"), Galaxy.MILKYWAY, 0, EnumFacing.NORTH);

    public StarSystem(ResourceLocation name, Galaxy galaxy, double distanceToSunLy, EnumFacing directionToSun) {
        super(new ResourceLocation(name.getResourceDomain(), "system:" + name.getResourcePath()), galaxy, distanceToSunLy, directionToSun);
    }

}

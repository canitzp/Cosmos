package de.canitzp.cosmos.spaceobjects.space;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.Util;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * @author canitzp
 */
public class Planet extends SpaceObject<StarSystem> {

    public static final Planet EARTH = new Planet(new ResourceLocation(Cosmos.MODID, "earth"), StarSystem.SOLAR_SYSTEM, Util.astronomicalUnitsToLightYears(1D), EnumFacing.NORTH);

    public Planet(ResourceLocation name, StarSystem starSystem, double distanceToSunLy, EnumFacing directionToSun) {
        super(new ResourceLocation(name.getResourceDomain(), "planet:" + name.getResourcePath()), starSystem, distanceToSunLy, directionToSun);
    }

    public Planet(ResourceLocation name, StarSystem starSystem){
        this(name, starSystem, starSystem.getDistanceToSun(), starSystem.getDirectionToSun());
    }

    @Override
    public String toString() {
        return "Planet{name=" + this.getRegisterName().getResourcePath().substring(7) + "; distanceToSun=" + Util.lightYearsToAstronomicalUnits(this.getDistanceToSun()) + "AU}";
    }

}

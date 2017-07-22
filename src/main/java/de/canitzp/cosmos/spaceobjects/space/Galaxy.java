package de.canitzp.cosmos.spaceobjects.space;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.Util;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * @author canitzp
 */
public class Galaxy extends SpaceObject<GalacticGroup> {

    public static final Galaxy MILKYWAY = new Galaxy(new ResourceLocation(Cosmos.MODID, "milkyway"), GalacticGroup.LOCAL_GROUP, Util.parsecToLightYears(12000), EnumFacing.SOUTH);

    public Galaxy(ResourceLocation name, @Nonnull GalacticGroup galacticGroup, double distanceToSunLy, EnumFacing directionToSun) {
        super(new ResourceLocation(name.getResourceDomain(), "galaxy:" + name.getResourcePath()), galacticGroup, distanceToSunLy, directionToSun);
    }

}

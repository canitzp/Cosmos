package de.canitzp.cosmos.spaceobjects.space;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.Util;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author canitzp
 */
public class GalacticGroup extends SpaceObject{

    public static final GalacticGroup LOCAL_GROUP = new GalacticGroup(new ResourceLocation(Cosmos.MODID, "local_group"), Util.parsecToLightYears(3000000) / 5.0D, EnumFacing.WEST);

    public GalacticGroup(ResourceLocation name, double distanceToSunLy, EnumFacing directionToSun) {
        super(new ResourceLocation(name.getResourceDomain(), "group:" + name.getResourcePath()), null, distanceToSunLy, directionToSun);
    }

}

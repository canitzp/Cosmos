package de.canitzp.cosmos;

import de.canitzp.cosmos.machine.launchpad.BlockLaunchPad;
import de.canitzp.cosmos.machine.spaceeye.BlockSpaceEye;
import de.canitzp.cosmos.spaceobjects.part.AntennaPart;
import de.canitzp.cosmos.spaceobjects.part.EPartWeight;

/**
 * @author canitzp
 */
public class CosmosRegistry {

    /**
     * Blocks:
     */
    public static BlockSpaceEye spaceEye;
    public static BlockLaunchPad launchPad;

    /**
     * Parts:
     */
    public static AntennaPart antennaShortRange = new AntennaPart("short_range", EPartWeight.LIGHT);

}

package de.canitzp.cosmos;

import de.canitzp.cosmos.machine.launchpad.BlockLaunchPad;
import de.canitzp.cosmos.machine.spaceeye.BlockSpaceEye;
import de.canitzp.ctpcore.registry.MCRegistry;
import de.canitzp.ctpcore.registry.Register;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author canitzp
 */
public class CosmosRegistry {

    @Register
    public static BlockSpaceEye spaceEye;
    @Register
    public static BlockLaunchPad launchPad;

}

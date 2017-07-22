package de.canitzp.cosmos.machine.launchpad;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.ctpcore.base.BlockContainerBase;
import de.canitzp.ctpcore.property.ExtendedDirection;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * @author canitzp
 */
public class BlockLaunchPad extends BlockContainerBase {

    public BlockLaunchPad() {
        super(Material.IRON, new ResourceLocation(Cosmos.MODID, "launchpad"), TileLaunchPad.class);
        this.setCreativeTab(Cosmos.tab);
    }

    @Override
    public ExtendedDirection getFacing() {
        return HORIZONTAL;
    }
}

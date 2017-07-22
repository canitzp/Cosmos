package de.canitzp.cosmos.machine.spaceeye;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.ctpcore.base.BlockContainerBase;
import de.canitzp.ctpcore.inventory.FakeContainer;
import de.canitzp.ctpcore.property.ExtendedDirection;
import de.canitzp.ctpcore.tile.FakeTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * @author canitzp
 */
public class BlockSpaceEye extends BlockContainerBase {

    public BlockSpaceEye() {
        super(Material.IRON, new ResourceLocation(Cosmos.MODID, "space_eye"), FakeTileEntity.class);
        this.setCreativeTab(Cosmos.tab);
        this.addGuiContainer(Cosmos.instance, GuiSpaceEye.class, FakeContainer.class);
    }

    @Override
    public ExtendedDirection getFacing() {
        return HORIZONTAL;
    }

}

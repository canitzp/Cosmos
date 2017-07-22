package de.canitzp.cosmos.item;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.ctpcore.base.ItemBase;
import net.minecraft.util.ResourceLocation;

/**
 * @author canitzp
 */
public class ItemSpace extends ItemBase {

    public ItemSpace(ResourceLocation name) {
        super(name);
        this.setCreativeTab(Cosmos.tab);
        this.setMaxStackSize(1);
    }

}

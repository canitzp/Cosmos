package de.canitzp.cosmos.spaceobjects.part;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.spaceobjects.Spacecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * @author canitzp
 */
public class AntennaPart extends Part {

    public AntennaPart(String identifier, EPartWeight partWeight) {
        super(new ResourceLocation(Cosmos.MODID, "antenna." + identifier), EPartTypes.ANTENNA, partWeight);
    }

    @Nonnull
    @Override
    public NonNullList<ItemStack> craftingIngredients(Spacecraft spacecraft) {
        NonNullList<ItemStack> stacks = NonNullList.create();
        stacks.add(new ItemStack(Items.IRON_INGOT, 64));
        return stacks;
    }

    @Override
    public boolean canAttachedTo(Spacecraft spacecraft) {
        return true;
    }

    @Override
    public PartEntity createPartEntity(Spacecraft spacecraft) {
        return null;
    }

}

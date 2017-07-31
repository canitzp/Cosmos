package de.canitzp.cosmos.spaceobjects.part;

import de.canitzp.cosmos.spaceobjects.SpacePosition;
import de.canitzp.cosmos.spaceobjects.space.SpaceObject;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * @author canitzp
 */
public abstract class PartEntity {

    @Nonnull
    public abstract NBTTagCompound saveNBT();

    public abstract void loadNBT(@Nonnull NBTTagCompound nbt);

    public void updateSpacecraft(World world, SpacePosition pos){

    }

    public void onArriveAt(World world, SpaceObject spaceObject){

    }

}

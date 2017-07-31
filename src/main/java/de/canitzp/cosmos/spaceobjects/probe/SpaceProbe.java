package de.canitzp.cosmos.spaceobjects.probe;

import de.canitzp.cosmos.spaceobjects.SpacePosition;
import de.canitzp.cosmos.spaceobjects.Spacecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author canitzp
 */
public class SpaceProbe extends Spacecraft {

    public SpaceProbe(String name, SpacePosition position) {
        super(name, position);
    }

    public SpaceProbe(NBTTagCompound nbt) {
        super(nbt);
    }

    @Override
    public NBTTagCompound saveNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void loadNBT(NBTTagCompound nbt) {

    }
}

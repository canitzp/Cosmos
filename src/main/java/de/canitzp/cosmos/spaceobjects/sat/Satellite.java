package de.canitzp.cosmos.spaceobjects.sat;

import de.canitzp.cosmos.spaceobjects.SpacePosition;
import de.canitzp.cosmos.spaceobjects.Spacecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canitzp
 */
public class Satellite extends Spacecraft {

    private SatTypes type;

    public Satellite(String name, SpacePosition position, SatTypes type) {
        super(name, position);
        this.type = type;
    }

    public Satellite(NBTTagCompound nbt){
        super(nbt);
    }

    @Override
    public NBTTagCompound saveNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("SatType", this.type.ordinal());
        return nbt;
    }

    @Override
    public void loadNBT(NBTTagCompound nbt) {
        this.type = SatTypes.values()[nbt.getInteger("SatType")];
    }

    public List<String> getAttributes(){
        List<String> list = super.getAttributes();
        list.add("SatType:  " + this.type.name());
        return list;
    }
}

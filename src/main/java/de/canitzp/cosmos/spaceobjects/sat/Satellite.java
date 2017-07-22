package de.canitzp.cosmos.spaceobjects.sat;

import de.canitzp.cosmos.spaceobjects.SpacePosition;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canitzp
 */
public class Satellite implements INBTSerializable<NBTTagCompound> {

    private SpacePosition position;
    private String name;
    private SatTypes type;

    public Satellite(String name, SpacePosition position, SatTypes type) {
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public Satellite(NBTTagCompound nbt){
        this.name = nbt.getString("Name");
        this.position = new SpacePosition(nbt.getCompoundTag("Position"));
        this.type = SatTypes.values()[nbt.getInteger("SatType")];
    }

    public NBTTagCompound getRawData(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Name", this.name);
        nbt.setTag("Position", this.position.serializeNBT());
        nbt.setInteger("SatType", this.type.ordinal());
        return nbt;
    }

    public String getName() {
        return name;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {

    }

    @Override
    public String toString() {
        return String.format("Satellite{name=%s, position=%s}", getName(), this.position.toString());
    }

    public SpacePosition getPosition() {
        return position;
    }

    public List<String> getProperties(ArrayList<String> current){
        current.add("Position: " + this.position.getClosestLocationString(""));
        current.add("SatType:  " + this.type.name());
        return current;
    }
}

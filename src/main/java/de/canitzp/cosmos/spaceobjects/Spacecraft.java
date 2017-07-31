package de.canitzp.cosmos.spaceobjects;

import de.canitzp.cosmos.spaceobjects.part.Part;
import de.canitzp.cosmos.spaceobjects.part.PartEntity;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author canitzp
 */
public abstract class Spacecraft{

    private String name;
    private SpacePosition position;
    private Map<Part, PartEntity> attachedParts = new HashMap<>();

    public Spacecraft(String name, SpacePosition position){
        this.name = name;
        this.position = position;
    }

    public Spacecraft(NBTTagCompound nbt){
        this.loadNBT(nbt);
    }

    public final NBTTagCompound getRawData(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Name", this.name);
        nbt.setTag("Position", this.position.serializeNBT());
        nbt.setTag("Data", this.saveNBT());
        NBTTagCompound parts = new NBTTagCompound();
        for(Map.Entry<Part, PartEntity> entry : this.attachedParts.entrySet()){
            parts.setTag(entry.getKey().getRegisterName().toString(), entry.getValue().saveNBT());
        }
        return nbt;
    }

    public abstract NBTTagCompound saveNBT();

    public abstract void loadNBT(NBTTagCompound nbt);

    public void init(){}

    public List<String> getAttributes(){
        List<String> list = new ArrayList<>();
        list.add("Position: " + this.position.getClosestLocationString(""));
        return list;
    }

    public String getName() {
        return name;
    }

    public SpacePosition getPosition() {
        return position;
    }

    public Spacecraft addPart(Part part){
        if(part.canAttachedTo(this)){
            this.attachedParts.put(part, part.createPartEntity(this));
        }
        return this;
    }

    public Spacecraft removePart(Part part){
        if(this.attachedParts.containsKey(part)){
            this.attachedParts.remove(part);
        }
        return this;
    }

    @Nullable
    public PartEntity getPartEntity(Part part){
        return this.attachedParts.getOrDefault(part, null);
    }

    @Override
    public String toString() {
        return String.format("Spacecraft{name=%s, position=%s}", getName(), getPosition().toString());
    }

}

package de.canitzp.cosmos.spaceobjects.space;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.ctpcore.registry.IRegistryEntry;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author canitzp
 */
public abstract class SpaceObject<T extends SpaceObject> implements IRegistryEntry{

    public static final Map<ResourceLocation, SpaceObject> REGISTRY = new HashMap<>();

    private ResourceLocation name;
    private T parent;
    private double distanceToSunLy;
    private EnumFacing directionToSun;
    private List<SpaceObject> childs = new ArrayList<>();

    protected SpaceObject(ResourceLocation name, T parent, double distanceToSunLy, EnumFacing directionToSun){
        this.name = name;
        this.parent = parent;
        this.distanceToSunLy = distanceToSunLy;
        if(!ArrayUtils.contains(EnumFacing.HORIZONTALS, directionToSun)){
            Cosmos.LOGGER.error(directionToSun + " is no horizontal direction and can't be used as direction!");
            directionToSun = EnumFacing.NORTH;
        }
        this.directionToSun = directionToSun;
    }

    @Override
    public IRegistryEntry[] getRegisterElements() {
        return new IRegistryEntry[]{this};
    }

    @Override
    public ResourceLocation getRegisterName() {
        return this.name;
    }

    @Override
    public void onRegister(IRegistryEntry[] otherEntries) {}

    @Override
    public void ownRegistry() {
        REGISTRY.put(this.getRegisterName(), this);
    }

    @Override
    public void registerRenderer() {

    }

    public String getLocalizedName(){
        return I18n.format(this.getRegisterName().toString());
    }

    @Override
    public String toString() {
        return String.format("SpaceObject{name=%s}", this.getRegisterName().toString());
    }

    public T getParent(){
        return this.parent;
    }

    public double getDistanceToSun(){
        return this.distanceToSunLy;
    }

    public EnumFacing getDirectionToSun(){
        return this.directionToSun;
    }

    public SpaceObject addChild(SpaceObject child){
        this.childs.add(child);
        return this;
    }

    public List<SpaceObject> getChilds() {
        return childs;
    }
}

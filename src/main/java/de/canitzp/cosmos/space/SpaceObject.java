package de.canitzp.cosmos.space;

import de.canitzp.ctpcore.registry.IRegistryEntry;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public class SpaceObject implements IRegistryEntry{

    public static final Map<ResourceLocation, SpaceObject> REGISTRY = new HashMap<>();

    private ResourceLocation name;

    public SpaceObject(ResourceLocation name){
        this.name = name;
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

}

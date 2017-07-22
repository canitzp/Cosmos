package de.canitzp.cosmos.spaceobjects.part;

import de.canitzp.ctpcore.registry.IRegistryEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public abstract class Part implements IRegistryEntry {

    public static final Map<ResourceLocation, Part> PART_REGISTRY = new HashMap<>();

    private ResourceLocation name;
    private EPartTypes partType;
    private EPartWeight partWeight;



    @Override
    public IRegistryEntry[] getRegisterElements() {
        return new IRegistryEntry[]{this};
    }

    @Override
    public ResourceLocation getRegisterName() {
        return this.name;
    }

    @Override
    public void onRegister(IRegistryEntry[] otherEntries) {
        PART_REGISTRY.put(this.getRegisterName(), this);
    }

    @Override
    public void ownRegistry() {}

    @Override
    public void registerRenderer() {}

    public abstract void addToItemStack(@Nonnull ItemStack stack);

    public abstract void removeFromItemStack(@Nonnull ItemStack stack);

    public EPartTypes getPartType() {
        return partType;
    }

    public EPartWeight getPartWeight() {
        return partWeight;
    }
}

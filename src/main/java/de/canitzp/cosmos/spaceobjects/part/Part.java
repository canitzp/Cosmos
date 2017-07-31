package de.canitzp.cosmos.spaceobjects.part;

import de.canitzp.cosmos.spaceobjects.SpacePosition;
import de.canitzp.cosmos.spaceobjects.Spacecraft;
import de.canitzp.cosmos.spaceobjects.space.Planet;
import de.canitzp.cosmos.spaceobjects.space.SpaceObject;
import de.canitzp.ctpcore.registry.IRegistryEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

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

    public Part(ResourceLocation name, EPartTypes partType, EPartWeight partWeight) {
        this.name = name;
        this.partType = partType;
        this.partWeight = partWeight;
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
    public void onRegister(IRegistryEntry[] otherEntries) {

    }

    @Override
    public void ownRegistry() {
        PART_REGISTRY.put(this.getRegisterName(), this);
    }

    @Override
    public void registerRenderer() {}

    @Nonnull
    public abstract NonNullList<ItemStack> craftingIngredients(Spacecraft spacecraft);

    public abstract boolean canAttachedTo(Spacecraft spacecraft);

    public abstract PartEntity createPartEntity(Spacecraft spacecraft);

    public EPartTypes getPartType() {
        return partType;
    }

    public EPartWeight getPartWeight() {
        return partWeight;
    }


}

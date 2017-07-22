package de.canitzp.cosmos.spaceobjects;

import de.canitzp.cosmos.spaceobjects.space.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author canitzp
 */
public class SpacePosition implements INBTSerializable<NBTTagCompound>{

    @Nullable private GalacticGroup currentGalacticGroup;
    @Nullable private Galaxy currentGalaxy;
    @Nullable private StarSystem currentStarSystem;
    @Nullable private Planet currentPlanet;

    public SpacePosition(){}

    public SpacePosition(@Nonnull GalacticGroup galacticGroup){
        this.setGroup(galacticGroup);
    }

    public SpacePosition(@Nonnull Galaxy galaxy){
        this.setGalaxy(galaxy);
    }

    public SpacePosition(@Nonnull StarSystem starSystem){
        this.setStarSystem(starSystem);
    }

    public SpacePosition(@Nonnull Planet planet){
        this.setPlanet(planet);
    }

    public SpacePosition(@Nonnull NBTTagCompound nbt){this.deserializeNBT(nbt);}

    public SpacePosition setGroup(@Nonnull GalacticGroup galacticGroup){
        this.currentGalacticGroup = galacticGroup;
        this.currentGalaxy = null;
        this.currentStarSystem = null;
        this.currentPlanet = null;
        return this;
    }

    public SpacePosition setGalaxy(@Nonnull Galaxy galaxy){
        this.setGroup(galaxy.getParent());
        this.currentGalaxy = galaxy;
        this.currentStarSystem = null;
        this.currentPlanet = null;
        return this;
    }

    public SpacePosition setStarSystem(@Nonnull StarSystem starSystem){
        this.setGalaxy(starSystem.getParent());
        this.currentStarSystem = starSystem;
        this.currentPlanet = null;
        return this;
    }

    public SpacePosition setPlanet(@Nonnull Planet planet){
        this.setStarSystem(planet.getParent());
        this.currentPlanet = planet;
        return this;
    }

    public SpacePosition setUnsafe(@Nullable GalacticGroup galacticGroup, @Nullable Galaxy galaxy, @Nullable StarSystem starSystem, @Nullable Planet planet){
        this.currentGalacticGroup = galacticGroup;
        this.currentGalaxy = galaxy;
        this.currentStarSystem = starSystem;
        this.currentPlanet = planet;
        return this;
    }

    public SpacePosition setUnsafeIfNotNull(@Nullable GalacticGroup galacticGroup, @Nullable Galaxy galaxy, @Nullable StarSystem starSystem, @Nullable Planet planet){
        if(galacticGroup != null) this.currentGalacticGroup = galacticGroup;
        if(galaxy != null) this.currentGalaxy = galaxy;
        if(starSystem != null) this.currentStarSystem = starSystem;
        if(planet != null) this.currentPlanet = planet;
        return this;
    }

    @Nullable
    public GalacticGroup getGroup() {
        return currentGalacticGroup;
    }

    @Nullable
    public Galaxy getGalaxy() {
        return currentGalaxy;
    }

    @Nullable
    public StarSystem getStarSystem() {
        return currentStarSystem;
    }

    @Nullable
    public Planet getPlanet() {
        return currentPlanet;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        if(this.currentGalacticGroup != null) nbt.setString("GalacticGroup", this.currentGalacticGroup.getRegisterName().toString());
        if(this.currentGalaxy != null) nbt.setString("Galaxy", this.currentGalaxy.getRegisterName().toString());
        if(this.currentStarSystem != null) nbt.setString("StarSystem", this.currentStarSystem.getRegisterName().toString());
        if(this.currentPlanet != null) nbt.setString("Planet", this.currentPlanet.getRegisterName().toString());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("GalacticGroup", Constants.NBT.TAG_STRING)){
            this.currentGalacticGroup = SpaceUtil.getByName(new ResourceLocation(nbt.getString("GalacticGroup")));
            if(nbt.hasKey("Galaxy", Constants.NBT.TAG_STRING)){
                this.currentGalaxy = SpaceUtil.getByName(new ResourceLocation(nbt.getString("Galaxy")));
                if(nbt.hasKey("StarSystem", Constants.NBT.TAG_STRING)){
                    this.currentStarSystem = SpaceUtil.getByName(new ResourceLocation(nbt.getString("StarSystem")));
                    if(nbt.hasKey("Planet", Constants.NBT.TAG_STRING)){
                        this.currentPlanet = SpaceUtil.getByName(new ResourceLocation(nbt.getString("Planet")));
                    }
                }
            }
        } else {
            this.setPlanet(Planet.EARTH);
        }
    }

    @Override
    public String toString() {
        return String.format("SpacePosition{group=%s, galaxy=%s, starSystem=%s, planet=%s}", getGroup(), getGalaxy(), getStarSystem(), getPlanet());
    }

    public String getClosestLocationString(String prefix){
        if(this.currentPlanet != null){
            return prefix + this.currentPlanet.getLocalizedName();
        } else if(this.currentStarSystem != null){
            return prefix + this.currentStarSystem.getLocalizedName();
        } else if(this.currentGalaxy != null){
            return prefix + this.currentGalaxy.getLocalizedName();
        } else if(this.currentGalacticGroup != null){
            return prefix + this.currentGalacticGroup.getLocalizedName();
        }
        return prefix;
    }

    public SpaceObject getLowestNonNullSpaceObject(){
        if(this.currentPlanet != null){
            return this.currentPlanet;
        } else if(this.currentStarSystem != null){
            return this.currentStarSystem;
        } else if (this.currentGalaxy != null){
            return this.currentGalaxy;
        }
        return this.currentGalacticGroup;
    }

    public double getTravelDistance(SpacePosition other){
        double myDistanceToSun = this.getLowestNonNullSpaceObject().getDistanceToSun();
        EnumFacing myDirectionToSun = this.getLowestNonNullSpaceObject().getDirectionToSun();
        double otherDistanceToSun = other.getLowestNonNullSpaceObject().getDistanceToSun();
        EnumFacing otherDirectionToSun = other.getLowestNonNullSpaceObject().getDirectionToSun();
        if(myDirectionToSun == otherDirectionToSun){
            return Math.abs(myDistanceToSun - otherDistanceToSun);
        } else if(myDirectionToSun.getOpposite() == otherDirectionToSun){
            return myDistanceToSun + otherDistanceToSun;
        }
        return 0.5D * (myDistanceToSun + otherDistanceToSun);
    }
}

package de.canitzp.cosmos.spaceobjects.part;

import de.canitzp.cosmos.Cosmos;
import net.minecraft.client.resources.I18n;

/**
 * @author canitzp
 */
public enum EPartTypes {

    ANTENNA("antenna"),
    ;

    private String name;

    EPartTypes(String name){
        this.name = name;
    }

    public String getUnlocalizedName(){
        return this.name;
    }

    public String getLocalizedName(){
        return I18n.format(Cosmos.MODID + ":part." + this.getUnlocalizedName() + ".name");
    }

}

package de.canitzp.cosmos;

import de.canitzp.cosmos.space.StarSystem;
import de.canitzp.ctpcore.CTPCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author canitzp
 */
@Mod(modid = Cosmos.MODID, name = Cosmos.MODNAME, version = Cosmos.VERSION)
public class Cosmos {

    public static final String MODID = "cosmos";
    public static final String MODNAME = "Cosmos";
    public static final String VERSION = "@VERSION@";

    public static final Logger LOGGER = LogManager.getLogger(MODNAME);

    @Mod.Instance(MODID)
    public static Cosmos instance;

    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(CosmosRegistry.spaceEye);
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        LOGGER.info("[PreInitialization] Startup " + MODNAME );
        LOGGER.info("[PreInitialization] Creating unlimited space with Planets, Galaxies and more");
        EnumSpaceObjects.preInit(event);
        LOGGER.info("[PreInitialization] Registering Blocks and Items");
        CosmosRegistry.preInit(event);
        System.out.println(SpaceUtil.getPlanets(EnumSpaceObjects.SOLAR_SYSTEM.getInstance(StarSystem.class)));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        CTPCore.init(instance, event);
    }

}

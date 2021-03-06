package de.canitzp.cosmos.data;

import de.canitzp.cosmos.Cosmos;
import de.canitzp.cosmos.spaceobjects.Spacecraft;
import de.canitzp.cosmos.spaceobjects.sat.Satellite;
import de.canitzp.cosmos.spaceobjects.probe.SpaceProbe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author canitzp
 */
@Mod.EventBusSubscriber(modid = Cosmos.MODID)
public class SpaceData extends WorldSavedData{

    public static final String FILE_NAME = "space_data";

    public static final List<SpaceProbe> probes = new ArrayList<>();
    public static final List<Satellite> satellites = new ArrayList<>();
    public static final List<Spacecraft> spacecrafts = new ArrayList<>();

    public SpaceData(String fileName) {
        super(fileName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        probes.clear();
        satellites.clear();
        spacecrafts.clear();

        if(nbt.hasKey("Spacecrafts", Constants.NBT.TAG_COMPOUND)){
            NBTTagCompound spacecrafts = nbt.getCompoundTag("Spacecrafts");
            for(String key : spacecrafts.getKeySet()){
                if(spacecrafts.hasKey(key, Constants.NBT.TAG_COMPOUND)){
                    try {
                        NBTTagCompound data = spacecrafts.getCompoundTag(key);
                        Spacecraft spacecraft = (Spacecraft) Class.forName(data.getString("Class-Name")).getConstructor(NBTTagCompound.class).newInstance(data.getCompoundTag("Data"));
                        spacecraft.init();
                        SpaceData.spacecrafts.add(spacecraft);
                    } catch (Exception e){
                        Cosmos.LOGGER.error("An error occurred while reading a spacecraft!", e);
                    }

                }
            }
        }

        /*
        if(nbt.hasKey("probe-data", Constants.NBT.TAG_COMPOUND)){
            NBTTagCompound probeData = nbt.getCompoundTag("probe-data");
            for(String key : probeData.getKeySet()){
                if(probeData.hasKey(key, Constants.NBT.TAG_COMPOUND)){
                    try {
                        NBTTagCompound data = probeData.getCompoundTag(key);
                        SpaceProbe probe = (SpaceProbe) Class.forName(data.getString("Class-Name")).getConstructor(NBTTagCompound.class).newInstance(data.getCompoundTag("raw-data"));
                        probe.deserializeNBT(data.getCompoundTag("save-data"));
                        probes.add(probe);
                    } catch (Exception e){
                        Cosmos.LOGGER.error("An error occurred while reading all space probe data!", e);
                    }
                }
            }
        }
        if(nbt.hasKey("satellite-data", Constants.NBT.TAG_COMPOUND)){
            NBTTagCompound satData = nbt.getCompoundTag("satellite-data");
            for(String key : satData.getKeySet()) {
                if (satData.hasKey(key, Constants.NBT.TAG_COMPOUND)) {
                    try {
                        NBTTagCompound data = satData.getCompoundTag(key);
                        Satellite satellite = (Satellite) Class.forName(data.getString("Class-Name")).getConstructor(NBTTagCompound.class).newInstance(data.getCompoundTag("raw-data"));
                        satellite.deserializeNBT(data.getCompoundTag("save-data"));
                        satellites.add(satellite);
                    } catch (Exception e){
                        Cosmos.LOGGER.error("An error occurred while reading all satellite data!", e);
                    }
                }
            }
        }
        */
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        NBTTagCompound spacecraftData = new NBTTagCompound();
        for(Spacecraft spacecraft : spacecrafts){
            NBTTagCompound data = new NBTTagCompound();
            data.setString("Class-Name", spacecraft.getClass().getName());
            data.setTag("Data", spacecraft.getRawData());
        }
        compound.setTag("Spacecrafts", spacecraftData);

        /*
        NBTTagCompound probeData = new NBTTagCompound();
        for(SpaceProbe probe : probes){
            NBTTagCompound data = new NBTTagCompound();
            data.setString("Class-Name", probe.getClass().getName());
            data.setTag("raw-data", probe.getRawData());
            data.setTag("save-data", probe.serializeNBT());
            probeData.setTag(probe.getName(), data);
        }
        NBTTagCompound satelliteData = new NBTTagCompound();
        for(Satellite satellite : satellites){
            NBTTagCompound data = new NBTTagCompound();
            data.setString("Class-Name", satellite.getClass().getName());
            data.setTag("raw-data", satellite.getRawData());
            data.setTag("save-data", satellite.serializeNBT());
            satelliteData.setTag(satellite.getName(), data);
        }
        compound.setTag("probe-data", probeData);
        compound.setTag("satellite-data", satelliteData);
        */
        return compound;
    }

    @SubscribeEvent
    public static void loadWorldEvent(WorldEvent.Load event){
        worldInteraction(event.getWorld());
    }

    @SubscribeEvent
    public static void saveWorldEvent(WorldEvent.Save event){
        worldInteraction(event.getWorld());
    }

    private static void worldInteraction(World world){
        if(world != null && !world.isRemote){
            MapStorage storage = world.getPerWorldStorage();
            if(storage != null){
                WorldSavedData worldSavedData = storage.getOrLoadData(SpaceData.class, FILE_NAME);
                if(worldSavedData instanceof SpaceData){
                    worldSavedData.setDirty(true);
                } else {
                    SpaceData data = new SpaceData(FILE_NAME);
                    data.setDirty(true);
                    storage.setData(FILE_NAME, data);
                }
            }
        }
    }

}

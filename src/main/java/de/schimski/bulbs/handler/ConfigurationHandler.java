package de.schimski.bulbs.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static boolean smoothTextures;

    public static void init(File configFile) {

        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        LogHelper.info("Config Changed");
        if (event.modID.equals(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        smoothTextures = configuration.getBoolean("Smooth Textures",Configuration.CATEGORY_GENERAL,true,"Use smooth Textures");
        LogHelper.info(smoothTextures);
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}

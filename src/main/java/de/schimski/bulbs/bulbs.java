package de.schimski.bulbs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.init.ModItems;
import de.schimski.bulbs.init.ModRenderers;
import de.schimski.bulbs.init.ModTileEntities;
import de.schimski.bulbs.proxy.IProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.utility.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class bulbs {

    @Mod.Instance(Reference.MOD_ID)
    public static bulbs instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        LogHelper.info("PreInitialization Complete");

        ModItems.init();
        ModBlocks.init();
        ModRenderers.init();
        ModTileEntities.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}

package de.schimski.bulbs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.init.*;
import de.schimski.bulbs.network.messageBulbs;
import de.schimski.bulbs.network.messageBulbsHandler;
import de.schimski.bulbs.proxy.IProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.utility.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class bulbs {

    @Mod.Instance(Reference.MOD_ID)
    public static bulbs instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();
        ModBlocks.init();
        ModTileEntities.init();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
        network.registerMessage(messageBulbsHandler.class, messageBulbs.class, 0, Side.CLIENT);

        LogHelper.info("PreInitialization Complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        Recipes.init();

        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
        LogHelper.info("PostInitialization Complete");
    }

}

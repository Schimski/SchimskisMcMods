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
import de.schimski.bulbs.client.gui.TextOverlay;
import de.schimski.bulbs.client.handler.KeyInputEventHandler;
import de.schimski.bulbs.handler.BulbsEventHandler;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.handler.GuiHandler;
import de.schimski.bulbs.init.*;
import de.schimski.bulbs.network.messageBulbs;
import de.schimski.bulbs.network.messageBulbsHandler;
import de.schimski.bulbs.proxy.IProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

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

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new TextOverlay(Minecraft.getMinecraft()));


        proxy.registerKeybindings();

        ModItems.init();
        ModBlocks.init();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
        network.registerMessage(messageBulbsHandler.class, messageBulbs.class, 0, Side.CLIENT);

        LogHelper.info("PreInitialization Complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());

        MinecraftForge.EVENT_BUS.register(new BulbsEventHandler());

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        // Initialize mod tile entities
        proxy.registerTileEntities();

        Recipes.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("PostInitialization Complete");
    }

    /*
     *  Extremely official ToDo:
     *
     *  - Change thinLight textures to match the bulbcolor not only for alpha
     */

}

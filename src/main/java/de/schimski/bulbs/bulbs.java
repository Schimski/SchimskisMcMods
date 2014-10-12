package de.schimski.bulbs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.handler.PacketHandler;
import de.schimski.bulbs.init.*;
import de.schimski.bulbs.proxy.CommonProxy;
import de.schimski.bulbs.proxy.IProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.item.crafting.RecipesIngots;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class bulbs {

    @Mod.Instance(Reference.MOD_ID)
    public static bulbs instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        LogHelper.info("PreInitialization Complete");

        ModItems.init();
        ModBlocks.init();
        ModTileEntities.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        Recipes.init();
        PacketHandler.initPackets();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}

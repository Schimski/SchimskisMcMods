package de.schimski.bulbs.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.tileEntity.*;

public abstract class CommonProxy implements IProxy{
    public void registerTileEntities()
    {

        GameRegistry.registerTileEntity(TileEntityGridLight.class, "gridLight");
        GameRegistry.registerTileEntity(TileEntityThinLight.class, "thinLight");
        GameRegistry.registerTileEntity(TileEntityOfficeLight1.class, "officeLight1");
        GameRegistry.registerTileEntity(TileEntityBlockLight.class, "blockLight");
        GameRegistry.registerTileEntity(TileEntityPoleLight.class, "poleLight");
        //GameRegistry.registerTileEntityWithAlternatives(TileEntityGridLight.class, "gridLight", "tile.GridLight);
        //GameRegistry.registerTileEntityWithAlternatives(TileEntityGlassBell.class, Names.Blocks.GLASS_BELL, "tile." + Names.Blocks.GLASS_BELL);
        //GameRegistry.registerTileEntity(TileEntityResearchStation.class, Names.Blocks.RESEARCH_STATION);

        /*
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChest.class, Names.Blocks.ALCHEMICAL_CHEST, "tile." + Names.Blocks.ALCHEMICAL_CHEST);
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestSmall.class, Names.Blocks.ALCHEMICAL_CHEST + "Small", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Small");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestMedium.class, Names.Blocks.ALCHEMICAL_CHEST + "Medium", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Medium");
        GameRegistry.registerTileEntityWithAlternatives(TileEntityAlchemicalChestLarge.class, Names.Blocks.ALCHEMICAL_CHEST + "Large", "tile." + Names.Blocks.ALCHEMICAL_CHEST + "Large");
        */
    }
}

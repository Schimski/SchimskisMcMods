package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;

public class ModTileEntities {
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityGridLight.class, "gridLight");

    }
}

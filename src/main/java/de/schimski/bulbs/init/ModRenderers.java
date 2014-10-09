package de.schimski.bulbs.init;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.schimski.bulbs.renderer.RendererGridLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;

public class ModRenderers {
    public static void init()
    {

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGridLight.class, new RendererGridLight());
    }
}

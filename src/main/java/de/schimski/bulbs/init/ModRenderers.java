package de.schimski.bulbs.init;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.renderer.RendererGridLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;

public class ModRenderers {
    public static void init()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGridLight.class, new RendererGridLight());
    }
}

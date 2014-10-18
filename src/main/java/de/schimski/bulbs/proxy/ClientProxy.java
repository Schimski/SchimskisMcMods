package de.schimski.bulbs.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import de.schimski.bulbs.renderer.RendererGridLight;
import de.schimski.bulbs.renderer.RendererThinLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.tileEntity.TileEntityThinLight;

public class ClientProxy extends CommonProxy {

    @Override
    public void initRenderingAndTextures() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGridLight.class, new RendererGridLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityThinLight.class, new RendererThinLight());
    }
}

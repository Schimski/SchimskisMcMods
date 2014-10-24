package de.schimski.bulbs.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.schimski.bulbs.block.BlockThinLight;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.renderer.*;
import de.schimski.bulbs.tileEntity.*;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    public static int renderPass;

    public static int thinLightRenderType;

    public void setCustomRenderers()
    {

    }

    @Override
    public void initRenderingAndTextures() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGridLight.class, new RendererGridLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityThinLight.class, new RendererThinLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOfficeLight1.class, new RendererOfficeLight1());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockLight.class, new RendererBlockLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPoleLight.class, new RendererPoleLight());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.thinLight), new RendererItemThinLight());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.gridLight), new RendererItemGridLight());

        thinLightRenderType = RenderingRegistry.getNextAvailableRenderId();
        //RenderingRegistry.registerBlockHandler(new RendererThinLight());
    }
}

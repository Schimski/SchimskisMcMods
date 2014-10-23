package de.schimski.bulbs.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import de.schimski.bulbs.block.BlockThinLight;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.renderer.RendererGridLight;
import de.schimski.bulbs.renderer.RendererItemGridLight;
import de.schimski.bulbs.renderer.RendererItemThinLight;
import de.schimski.bulbs.renderer.RendererThinLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.tileEntity.TileEntityThinLight;
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

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.thinLight), new RendererItemThinLight());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.gridLight), new RendererItemGridLight());

        thinLightRenderType = RenderingRegistry.getNextAvailableRenderId();
        //RenderingRegistry.registerBlockHandler(new RendererThinLight());
    }
}

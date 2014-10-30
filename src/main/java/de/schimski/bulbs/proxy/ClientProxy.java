package de.schimski.bulbs.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.block.BlockThinLight;
import de.schimski.bulbs.client.settings.Keybindings;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.renderer.*;
import de.schimski.bulbs.tileEntity.*;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import javax.swing.text.JTextComponent;

public class ClientProxy extends CommonProxy {

    public static int renderPass;

    public static boolean screwDriverSelected;

    public static int thinLightRenderType;

    @Override
    public void initRenderingAndTextures() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGridLight.class, new RendererGridLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityThinLight.class, new RendererThinLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOfficeLight1.class, new RendererOfficeLight1());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOfficeLight2.class, new RendererOfficeLight2());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockLight.class, new RendererBlockLight());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPoleLight.class, new RendererPoleLight());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.thinLight), new RendererItemThinLight());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.gridLight), new RendererItemGridLight());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.officeLight1), new RendererItemOfficeLight1());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.officeLight2), new RendererItemOfficeLight2());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.poleLight), new RendererItemPoleLight());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockLight), new RendererItemBlockLight());

        thinLightRenderType = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerKeybindings() {
        ClientRegistry.registerKeyBinding(Keybindings.keyX);
        ClientRegistry.registerKeyBinding(Keybindings.keyY);
        ClientRegistry.registerKeyBinding(Keybindings.keyZ);
        ClientRegistry.registerKeyBinding(Keybindings.keyMODE);
        ClientRegistry.registerKeyBinding(Keybindings.keyDIRECTION);

    }
}

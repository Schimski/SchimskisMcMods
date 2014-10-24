package de.schimski.bulbs.client.gui.inventory;

import de.schimski.bulbs.inventory.ContainerOfficeLight1;
import de.schimski.bulbs.inventory.ContainerPoleLight;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight1;
import de.schimski.bulbs.tileEntity.TileEntityPoleLight;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiPoleLight extends GuiContainer{

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/thinLight.png");

    public GuiPoleLight(InventoryPlayer inventoryPlayer, TileEntityPoleLight tileEntityPoleLight) {
        super(new ContainerPoleLight(inventoryPlayer, tileEntityPoleLight));
        this.xSize = 229;
        this.ySize = 184;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRendererObj.drawString("OfficeLight", 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}

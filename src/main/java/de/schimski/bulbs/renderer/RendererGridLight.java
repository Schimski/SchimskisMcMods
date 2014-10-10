package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelGridLight;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import de.schimski.bulbs.block.BlockGridLight;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RendererGridLight extends TileEntitySpecialRenderer{

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/models/gridLight.png");
    private ModelGridLight model;
    static int myRenderID;

    public RendererGridLight() {
        this.model = new ModelGridLight();
    }

    private void alignTileEntityAccordingMetadata(double x, double y, double z, int metadata)
    {
        if (metadata == 0) {
            GL11.glTranslatef((float)x + 0.5f, (float)y - 0.5f, (float)z + 0.5f);
        } else if (metadata == 1) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
            GL11.glRotatef(180, 1, 0, 0);
        } else if (metadata == 2) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z - 0.5f);
            GL11.glRotatef(90, 0, -1, 0);
            GL11.glRotatef(90, 0, 0, -1);;
        } else if (metadata == 3) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 1.5f);
            GL11.glRotatef(90, -1, 0, 0);
        } else if (metadata == 4) {
            GL11.glTranslatef((float)x - 0.5f, (float)y + 0.5f, (float)z + 0.5f);
            GL11.glRotatef(90, 0, 0, -1);
        } else if (metadata == 5) {
            GL11.glTranslatef((float)x + 1.5f, (float)y + 0.5f, (float)z + 0.5f);;
            GL11.glRotatef(90, 0, 0, 1);
        }
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_) {
        GL11.glPushMatrix();
        alignTileEntityAccordingMetadata(x, y, z, p_147500_1_.blockMetadata);
        this.bindTexture(texture);
            GL11.glPushMatrix();
            this.model.renderModel(0.0625f);
            GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

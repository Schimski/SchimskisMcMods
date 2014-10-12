package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelGridLight;
import assets.bulbs.models.ModelGridLightCon1;
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

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/models/gridLightCon1.png");
   // private static final ResourceLocation textureCon1 = new ResourceLocation(Reference.MOD_ID, "textures/models/gridLightCon1.png");
    private ModelGridLightCon1 model;
   // private ModelGridLightCon1 modelCon1;
    static int myRenderID;

    public RendererGridLight() {
        this.model = new ModelGridLightCon1();
     //   this.modelCon1 = new ModelGridLightCon1();
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
            GL11.glRotatef(90, 0, 0, -1);
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
//        TileEntityGridLight tileGridLight= (TileEntityGridLight)(p_147500_1_);
//        tileGridLight.readFromNBT(tileGridLight.nbtTag);

        GL11.glPushMatrix();
        alignTileEntityAccordingMetadata(x, y, z, p_147500_1_.blockMetadata);
        this.bindTexture(texture);
            GL11.glPushMatrix();
   //         if (tileGridLight.rightConnect) {
   //             this.modelCon1.renderModel(0.0625f);
   //         } else {
                this.model.renderModel(0.0625f);
   //         }
            GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

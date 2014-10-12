package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelGridLight;
import assets.bulbs.models.ModelGridLightCon1;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import de.schimski.bulbs.block.BlockGridLight;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.utility.LogHelper;
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
    private static final ResourceLocation textureCon1 = new ResourceLocation(Reference.MOD_ID, "textures/models/gridLightCon1.png");
    private ModelGridLight model;
    private ModelGridLightCon1 modelCon1;
    static int myRenderID;



    public RendererGridLight() {
        this.model = new ModelGridLight();
        this.modelCon1 = new ModelGridLightCon1();
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

    private void rotateTilEntityAccordingNBT(TileEntityGridLight gridLight)
    {
        for (int i = 0; i<4; i++)
        {
            if (gridLight.hasGridLightNeighbour(i))
            {
                GL11.glRotatef(i*90,0,1,0);
            }
        }
    }

    private void renderModel(TileEntityGridLight gridLight)
    {

        //LogHelper.info(gridLight.neighbourCount());
        if (gridLight.neighbourCount() == 1) {
            this.modelCon1.renderModel(0.0625f);
        } else {
            this.model.renderModel(0.0625f);
        }
    }

    private void bindTextureToModel(TileEntityGridLight gridLight)
    {
        if (gridLight.neighbourCount() == 1) {
            this.bindTexture(textureCon1);
        } else {
            this.bindTexture(texture);
        }
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_)
    {
        entity.getWorldObj().scheduleBlockUpdate(entity.xCoord, entity.yCoord, entity.zCoord, entity.getWorldObj().getBlock( entity.xCoord ,entity.yCoord,entity.zCoord), 10);
        TileEntityGridLight gridLight = (TileEntityGridLight)(entity);

//      tileGridLight.readFromNBT(tileGridLight.nbtTag);

        //LogHelper.info(gridLight.rightConnect);

        GL11.glPushMatrix();
        alignTileEntityAccordingMetadata(x, y, z, gridLight.blockMetadata);
        rotateTilEntityAccordingNBT(gridLight);
        bindTextureToModel(gridLight);
            GL11.glPushMatrix();
            renderModel(gridLight);
            GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

package de.schimski.bulbs.renderer;

import assets.bulbs.models.*;
import assets.bulbs.models.ModelOfficeLight1.ModelOfficeLight1;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight1;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RendererOfficeLight1 extends TileEntitySpecialRenderer{

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] officeLight1Types = new String[] {"officeLight1", "officeLight1Black", "officeLight1Red", "officeLight1Green", "officeLight1Brown", "officeLight1Blue", "officeLight1Purple", "officeLight1Cyan", "officeLight1LightGray", "officeLight1Gray", "officeLight1Pink", "officeLight1LimeGreen", "officeLight1Yellow", "officeLight1LightBlue", "officeLight1Magenta", "officeLight1Orange", "officeLight1White"};

    private ModelOfficeLight1 modelOfficeLight1;

    private float renderScale = 0.03125f; //0.0625f

    public RendererOfficeLight1() {
        modelOfficeLight1 = new ModelOfficeLight1();

        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/officeLight1/" + officeLight1Types[i] + "X32.png");
        }
    }

    private void alignTileEntityAccordingState(double x, double y, double z, int state)
    {
        if (state == 0) {
            GL11.glTranslatef((float)x + 0.5f, (float)y +0.25f, (float)z + 0.5f);
        } else if (state == 1) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.75f, (float)z + 0.5f);
            GL11.glRotatef(180, 1, 0, 0);
        } else if (state == 2) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z +0.25f);
            GL11.glRotatef(90, 0, -1, 0);
            GL11.glRotatef(90, 0, 0, -1);
        } else if (state == 3) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.75f);
            GL11.glRotatef(90, -1, 0, 0);
        } else if (state == 4) {
            GL11.glTranslatef((float)x +0.25f, (float)y + 0.5f, (float)z + 0.5f);
            GL11.glRotatef(90, 0, 0, -1);
        } else if (state == 5) {
            GL11.glTranslatef((float) x + 0.75f, (float) y + 0.5f, (float) z + 0.5f);
            GL11.glRotatef(90, 0, 0, 1);
        }
    }

    private void rotateTilEntityAccordingNBT(TileEntityOfficeLight1 officeLight1, int side)
    {
       /*if (officeLight1.neighbourCount() == 1 || officeLight1.neighboursAreClose() || officeLight1.neighbourCount() == 3) {
            for (int i = 0; i < 4; i++) {
                if (officeLight1.neighbourCount() == 1) {
                    if (officeLight1.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && side == 1) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && side == 3) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && side == 2) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && side == 4) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    }
                } else if (officeLight1.neighbourCount() == 2 && officeLight1.neighboursAreClose()) {
                    int j = (i < 3) ? i + 1 : 0;
                    if (officeLight1.hasConnectingLightNeighbour(i) && officeLight1.hasConnectingLightNeighbour(j) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && officeLight1.hasConnectingLightNeighbour(j) && side == 1) {
                        GL11.glRotatef((i+1) * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && officeLight1.hasConnectingLightNeighbour(j) && side == 2) {
                        GL11.glRotatef((i * 90), 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && officeLight1.hasConnectingLightNeighbour(j) && side == 3) {
                        GL11.glRotatef(((i+1) * 90), 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && officeLight1.hasConnectingLightNeighbour(j) && side == 4) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (officeLight1.hasConnectingLightNeighbour(i) && officeLight1.hasConnectingLightNeighbour(j) && side == 5) {
                        GL11.glRotatef((i+2) * 90, 0, 1, 0);
                    }
                }
                else if (officeLight1.neighbourCount() == 3)
                {
                    if (!officeLight1.hasConnectingLightNeighbour(i) && (side == 1 || side == 3)) {
                        GL11.glRotatef((i - 1) * 90, 0, 1, 0);
                    }
                    else if (!officeLight1.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef((3-i) * 90, 0, 1, 0);
                    }
                    else if (!officeLight1.hasConnectingLightNeighbour(i) && (side == 4 || side == 2)) {
                        GL11.glRotatef((-2+i) * 90, 0, 1, 0);
                    }
                    else if (!officeLight1.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    }
                }
            }
        }
        if (officeLight1.neighbourCount() == 2 && !officeLight1.neighboursAreClose())
        {
            if (officeLight1.hasConnectingLightNeighbour(2)  && (side == 4 || side == 5 || side == 2))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
            else if (officeLight1.hasConnectingLightNeighbour(1) && (side == 3 || side == 0 || side == 1))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
        }*/
    }

    private void renderModel(TileEntityOfficeLight1 officeLight1)
    {
        /*
        if (officeLight1.neighbourCount() == 1) {
            modelOfficeLight1Con1X32.renderModel(renderScale);
        } else if ((officeLight1.neighbourCount() == 2) && officeLight1.neighboursAreClose()){
            modelOfficeLight1Con2CloseX32.renderModel(renderScale);
        }else if ((officeLight1.neighbourCount() == 2) && !officeLight1.neighboursAreClose()){
            modelOfficeLight1Con2FarX32.renderModel(renderScale);
        } else if (officeLight1.neighbourCount() == 3) {
            modelOfficeLight1Con3X32.renderModel(renderScale);
        } else if (officeLight1.neighbourCount() == 4) {
            modelOfficeLight1Con4X32.renderModel(renderScale);
        } else {
        //    this.model.renderModel(0.0625f);
            modelOfficeLight1X32.renderModel(renderScale);

        }*/
        modelOfficeLight1.renderModel(renderScale);
    }

    private void renderModelAlpha(TileEntityOfficeLight1 officeLight1) {
/*
        if (officeLight1.neighbourCount() == 1) {
            modelOfficeLight1Con1X32.renderAlpha(renderScale);
        } else if ((officeLight1.neighbourCount() == 2) && officeLight1.neighboursAreClose()){
            modelOfficeLight1Con2CloseX32.renderAlpha(renderScale);
        }else if ((officeLight1.neighbourCount() == 2) && !officeLight1.neighboursAreClose()){
            modelOfficeLight1Con2FarX32.renderAlpha(renderScale);
        } else if (officeLight1.neighbourCount() == 3) {
            modelOfficeLight1Con3X32.renderAlpha(renderScale);
        } else if (officeLight1.neighbourCount() == 4) {
            modelOfficeLight1Con4X32.renderAlpha(renderScale);
        } else {
            modelOfficeLight1X32.renderAlpha(renderScale);
        }*/
        modelOfficeLight1.renderAlpha(renderScale);
    }
    
    private void selectAndBindTexture(TileEntityOfficeLight1 officeLight1) {
        int textureIndex = 0;
        ItemStack stack = officeLight1.getStackInSlot(0);
        if (stack != null) {
            textureIndex = stack.getItemDamage()+1;
        }

        bindTexture(texture[textureIndex]);
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_)
    {
        TileEntityOfficeLight1 officeLight1 = (TileEntityOfficeLight1)(entity);

        //Tessellator tessellator = Tessellator.instance;
        //This will make your block brightness dependent from surroundings lighting.
        //tessellator.setColorOpaque_F(1,1,1);
        //tessellator.setBrightness(15728880);
/*
        int var5 = (int) (((float)entity.getWorldObj().getBlockLightValue(entity.xCoord, entity.yCoord, entity.zCoord)) / 15F *240F);

        LogHelper.info("LightValue0: " + (float)entity.getWorldObj().getBlockLightValue(entity.xCoord, entity.yCoord, entity.zCoord));
        LogHelper.info("LightValue: " + var5);
        int var6 = var5 % 65536;
        LogHelper.info("LightValue2: " + var6);
        int var7 = var5 / 65536;
        LogHelper.info("LightValue3: " + var7);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
*/

        GL11.glPushMatrix();
            alignTileEntityAccordingState(x, y, z, officeLight1.getState());
            rotateTilEntityAccordingNBT(officeLight1, officeLight1.getState());
            selectAndBindTexture(officeLight1);


        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);

            GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_CULL_FACE);
                renderModel(officeLight1);
                GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();

            GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_BLEND);
                renderModelAlpha(officeLight1);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();



        GL11.glPopMatrix();
    }
/*
    //Set the lighting stuff, so it changes it's brightness properly.
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        //float brightness = block.getBlockBrightness(world, i, j, k);
        //As of MC 1.7+ block.getBlockBrightness() has become block.getLightValue():
        float brightness = block.getLightValue(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
    }*/
}

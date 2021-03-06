package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelGridLightX32.*;
import de.schimski.bulbs.item.ItemBulbRainbow;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererGridLight extends TileEntitySpecialRenderer{

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] gridLightTypes = new String[] {"gridLight", "gridLightBlack", "gridLightRed", "gridLightGreen", "gridLightBrown", "gridLightBlue", "gridLightPurple", "gridLightCyan", "gridLightLightGray", "gridLightGray", "gridLightPink", "gridLightLimeGreen", "gridLightYellow", "gridLightLightBlue", "gridLightMagenta", "gridLightOrange", "gridLightWhite"};

    private ModelGridLightX32 modelGridLightX32;
    private ModelGridLightCon1X32 modelGridLightCon1X32;
    private ModelGridLightCon2FarX32 modelGridLightCon2FarX32;
    private ModelGridLightCon2CloseX32 modelGridLightCon2CloseX32;
    private ModelGridLightCon3X32 modelGridLightCon3X32;
    private ModelGridLightCon4X32 modelGridLightCon4X32;

    private float renderScale = 0.03125f; //0.0625f

    public RendererGridLight() {
        modelGridLightX32 = new ModelGridLightX32();
        modelGridLightCon1X32 = new ModelGridLightCon1X32();
        modelGridLightCon2FarX32 = new ModelGridLightCon2FarX32();
        modelGridLightCon2CloseX32 =  new ModelGridLightCon2CloseX32();
        modelGridLightCon3X32 = new ModelGridLightCon3X32();
        modelGridLightCon4X32 =  new ModelGridLightCon4X32();

        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/gridLight/" + gridLightTypes[i] + "X32.png");
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

    private void rotateTilEntityAccordingNBT(TileEntityGridLight gridLight, int side)
    {
        if (gridLight.neighbourCount() == 1 || gridLight.neighboursAreClose() || gridLight.neighbourCount() == 3) {
            for (int i = 0; i < 4; i++) {
                if (gridLight.neighbourCount() == 1) {
                    if (gridLight.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && side == 1) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && side == 3) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && side == 2) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && side == 4) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    }
                } else if (gridLight.neighbourCount() == 2 && gridLight.neighboursAreClose()) {
                    int j = (i < 3) ? i + 1 : 0;
                    if (gridLight.hasConnectingLightNeighbour(i) && gridLight.hasConnectingLightNeighbour(j) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && gridLight.hasConnectingLightNeighbour(j) && side == 1) {
                        GL11.glRotatef((i+1) * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && gridLight.hasConnectingLightNeighbour(j) && side == 2) {
                        GL11.glRotatef((i * 90), 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && gridLight.hasConnectingLightNeighbour(j) && side == 3) {
                        GL11.glRotatef(((i+1) * 90), 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && gridLight.hasConnectingLightNeighbour(j) && side == 4) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (gridLight.hasConnectingLightNeighbour(i) && gridLight.hasConnectingLightNeighbour(j) && side == 5) {
                        GL11.glRotatef((i+2) * 90, 0, 1, 0);
                    }
                }
                else if (gridLight.neighbourCount() == 3)
                {
                    if (!gridLight.hasConnectingLightNeighbour(i) && (side == 1 || side == 3)) {
                        GL11.glRotatef((i - 1) * 90, 0, 1, 0);
                    }
                    else if (!gridLight.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef((3-i) * 90, 0, 1, 0);
                    }
                    else if (!gridLight.hasConnectingLightNeighbour(i) && (side == 4 || side == 2)) {
                        GL11.glRotatef((-2+i) * 90, 0, 1, 0);
                    }
                    else if (!gridLight.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    }
                }
            }
        }
        if (gridLight.neighbourCount() == 2 && !gridLight.neighboursAreClose())
        {
            if (gridLight.hasConnectingLightNeighbour(2)  && (side == 4 || side == 5 || side == 2))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
            else if (gridLight.hasConnectingLightNeighbour(1) && (side == 3 || side == 0 || side == 1))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
        }
    }

    private void renderModel(TileEntityGridLight gridLight)
    {
        if (gridLight.neighbourCount() == 1) {
            modelGridLightCon1X32.renderModel(renderScale);
        } else if ((gridLight.neighbourCount() == 2) && gridLight.neighboursAreClose()){
            modelGridLightCon2CloseX32.renderModel(renderScale);
        }else if ((gridLight.neighbourCount() == 2) && !gridLight.neighboursAreClose()){
            modelGridLightCon2FarX32.renderModel(renderScale);
        } else if (gridLight.neighbourCount() == 3) {
            modelGridLightCon3X32.renderModel(renderScale);
        } else if (gridLight.neighbourCount() == 4) {
            modelGridLightCon4X32.renderModel(renderScale);
        } else {
        //    this.model.renderModel(0.0625f);
            modelGridLightX32.renderModel(renderScale);

        }
    }

    private void renderModelAlpha(TileEntityGridLight gridLight) {

        if (gridLight.neighbourCount() == 1) {
            modelGridLightCon1X32.renderAlpha(renderScale);
        } else if ((gridLight.neighbourCount() == 2) && gridLight.neighboursAreClose()){
            modelGridLightCon2CloseX32.renderAlpha(renderScale);
        }else if ((gridLight.neighbourCount() == 2) && !gridLight.neighboursAreClose()){
            modelGridLightCon2FarX32.renderAlpha(renderScale);
        } else if (gridLight.neighbourCount() == 3) {
            modelGridLightCon3X32.renderAlpha(renderScale);
        } else if (gridLight.neighbourCount() == 4) {
            modelGridLightCon4X32.renderAlpha(renderScale);
        } else {
            modelGridLightX32.renderAlpha(renderScale);
        }
    }

    private void selectAndBindTexture(TileEntityLightContainer entity) {
        int textureIndex = 0;
        ItemStack stack = entity.getStackInSlot(0);
        if (stack != null) {
            if (stack.getItem() instanceof ItemBulbRainbow) {
                textureIndex = entity.getPowerLevel()+1;
            } else {
                textureIndex = stack.getItemDamage()+1;
            }
        }

        bindTexture(texture[textureIndex]);
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_)
    {
        TileEntityGridLight gridLight = (TileEntityGridLight)(entity);
        GL11.glPushMatrix();
            alignTileEntityAccordingState(x, y, z, gridLight.getState());
            rotateTilEntityAccordingNBT(gridLight, gridLight.getState());
            selectAndBindTexture(gridLight);

            if (ClientProxy.renderPass == 0) {
                GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    renderModel(gridLight);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glPopMatrix();
            } else {
                GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_BLEND);
                    renderModelAlpha(gridLight);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }

        GL11.glPopMatrix();



    }
}

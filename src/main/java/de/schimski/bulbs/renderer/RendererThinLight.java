package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelThinLightX32.*;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.item.ItemBulbRainbow;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.tileEntity.TileEntityThinLight;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererThinLight extends TileEntitySpecialRenderer {

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] thinLightTypes = new String[] {"thinLight", "thinLightBlack", "thinLightRed", "thinLightGreen", "thinLightBrown", "thinLightBlue", "thinLightPurple", "thinLightCyan", "thinLightLightGray", "thinLightGray", "thinLightPink", "thinLightLimeGreen", "thinLightYellow", "thinLightLightBlue", "thinLightMagenta", "thinLightOrange", "thinLightWhite"};

    private ModelThinLightX32 modelThinLightX32;
    private ModelThinLightCon1X32 modelThinLightCon1X32;
    private ModelThinLightCon2FarX32 modelThinLightCon2FarX32;
    private ModelThinLightCon2CloseX32 modelThinLightCon2CloseX32;
    private ModelThinLightCon3X32 modelThinLightCon3X32;
    private ModelThinLightCon4X32 modelThinLightCon4X32;

    private float renderScale = 0.03125f; //0.0625f

    private String smoothTextures;

    public RendererThinLight() {
        this.modelThinLightX32 = new ModelThinLightX32();
        this.modelThinLightCon1X32 = new ModelThinLightCon1X32();
        this.modelThinLightCon2FarX32 = new ModelThinLightCon2FarX32();
        this.modelThinLightCon2CloseX32 = new ModelThinLightCon2CloseX32();
        this.modelThinLightCon3X32 = new ModelThinLightCon3X32();
        this.modelThinLightCon4X32 = new ModelThinLightCon4X32();

        smoothTextures = ConfigurationHandler.smoothTextures ? "" : "Noise";


        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/thinLight/" + thinLightTypes[i] + smoothTextures + "X32.png");
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
            GL11.glTranslatef((float)x + 0.75f, (float)y + 0.5f, (float)z + 0.5f);
            GL11.glRotatef(90, 0, 0, 1);
        }
    }

    private void rotateTilEntityAccordingNBT(TileEntityThinLight thinLight, int side)
    {
        if (thinLight.neighbourCount() == 1 || thinLight.neighboursAreClose() || thinLight.neighbourCount() == 3) {
            for (int i = 0; i < 4; i++) {
                if (thinLight.neighbourCount() == 1) {
                    if (thinLight.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 1) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 2) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 3) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 4) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    }
                } else if (thinLight.neighbourCount() == 2 && thinLight.neighboursAreClose()) {
                    int j = (i < 3) ? i + 1 : 0;
                    if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 1) {
                        GL11.glRotatef((i+1) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 2) {
                        GL11.glRotatef((i * 90), 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 3) {
                        GL11.glRotatef(((i+1) * 90), 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 4) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 5) {
                        GL11.glRotatef((i+2) * 90, 0, 1, 0);
                    }
                }
                else if (thinLight.neighbourCount() == 3) {
                    if (!thinLight.hasConnectingLightNeighbour(i) && (side == 1 || side == 3)) {
                        GL11.glRotatef((i - 1) * 90, 0, 1, 0);
                    }
                    else if (!thinLight.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef((3-i) * 90, 0, 1, 0);
                    }
                    else if (!thinLight.hasConnectingLightNeighbour(i) && (side == 4 || side == 2)) {
                        GL11.glRotatef((i+2) * 90, 0, 1, 0);
                    }
                    else if (!thinLight.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef((i) * 90, 0, 1, 0);
                    }
                }
            }
        } else if (thinLight.neighbourCount() == 2 && !thinLight.neighboursAreClose()) {
            if (thinLight.hasConnectingLightNeighbour(2)  && (side == 4 || side == 5 || side == 2))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
            else if (thinLight.hasConnectingLightNeighbour(1) && (side == 3 || side == 0 || side == 1))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
        } else if (thinLight.neighbourCount() == 0) {
            if (side == 4 || side == 5 || side == 2) {
                GL11.glRotatef(90, 0, 1, 0);
            }
        }
    }

    private void renderModel(TileEntityThinLight thinLight)
    {
        if (thinLight.neighbourCount() == 1) {
            modelThinLightCon1X32.renderModel(renderScale);
        } else if ((thinLight.neighbourCount() == 2) && thinLight.neighboursAreClose()){
            modelThinLightCon2CloseX32.renderModel(renderScale);
        }else if ((thinLight.neighbourCount() == 2) && !thinLight.neighboursAreClose()){
            modelThinLightCon2FarX32.renderModel(renderScale);
        } else if (thinLight.neighbourCount() == 3) {
            modelThinLightCon3X32.renderModel(renderScale);
        } else if (thinLight.neighbourCount() == 4) {
            modelThinLightCon4X32.renderModel(renderScale);
        } else {
            modelThinLightX32.renderModel(renderScale);
        }
    }

    private void renderModelAlpha(TileEntityThinLight thinLight) {

        if (thinLight.neighbourCount() == 1) {
           modelThinLightCon1X32.renderAlpha(renderScale);
        } else if ((thinLight.neighbourCount() == 2) && thinLight.neighboursAreClose()){
           modelThinLightCon2CloseX32.renderAlpha(renderScale);
        }else if ((thinLight.neighbourCount() == 2) && !thinLight.neighboursAreClose()){
           modelThinLightCon2FarX32.renderAlpha(renderScale);
        } else if (thinLight.neighbourCount() == 3) {
            modelThinLightCon3X32.renderAlpha(renderScale);
        } else if (thinLight.neighbourCount() == 4) {
            modelThinLightCon4X32.renderAlpha(renderScale);
        } else {
            this.modelThinLightX32.renderAlpha(renderScale);
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
        TileEntityThinLight thinLight = (TileEntityThinLight)(entity);
        GL11.glPushMatrix();
        alignTileEntityAccordingState(x, y, z, thinLight.getState());
        rotateTilEntityAccordingNBT(thinLight, thinLight.getState());

        selectAndBindTexture(thinLight);


        if (ClientProxy.renderPass == 0) {
            GL11.glPushMatrix();
               renderModel(thinLight);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_BLEND);
                renderModelAlpha(thinLight);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }

        GL11.glPopMatrix();
    }
}

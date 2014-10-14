package de.schimski.bulbs.renderer;

import assets.bulbs.models.*;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererGridLight extends TileEntitySpecialRenderer{

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/models/gridLight.png");
    private ModelGridLight model;
    private ModelGridLightCon1 modelCon1;
    private ModelGridLightCon2a modelCon2a;
    private ModelGridLightCon2b modelCon2b;
    private ModelGridLightCon3 modelCon3;
    static int myRenderID;



    public RendererGridLight() {
        this.model = new ModelGridLight();
        this.modelCon1 = new ModelGridLightCon1();
        this.modelCon2a = new ModelGridLightCon2a();
        this.modelCon2b = new ModelGridLightCon2b();
        this.modelCon3 = new ModelGridLightCon3();
        this.modelCon4 = new ModelGridLightCon4();
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

    private void rotateTilEntityAccordingNBT(TileEntityGridLight gridLight, int side)
    {
        if (gridLight.neighbourCount() == 1 || gridLight.neighboursAreClose() || gridLight.neighbourCount() == 3) {
            for (int i = 0; i < 4; i++) {
                if (gridLight.neighbourCount() == 1) {
                    if (gridLight.hasGridLightNeighbour(i) && side == 0) {
                        GL11.glRotatef(-i * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && side == 1) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && side == 3) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && side == 2) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && side == 4) {
                        GL11.glRotatef((i + 3) * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && side == 5) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    }
                } else if (gridLight.neighbourCount() == 2 && gridLight.neighboursAreClose()) {
                    int j = (i < 3) ? i + 1 : 0;
                    if (gridLight.hasGridLightNeighbour(i) && gridLight.hasGridLightNeighbour(j) && side == 0) {
                        GL11.glRotatef(-(i + 1) * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && gridLight.hasGridLightNeighbour(j) && side == 1) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && gridLight.hasGridLightNeighbour(j) && side == 2) {
                        GL11.glRotatef(-90 + (i * 90), 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && gridLight.hasGridLightNeighbour(j) && side == 3) {
                        GL11.glRotatef((i * 90), 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && gridLight.hasGridLightNeighbour(j) && side == 4) {
                        GL11.glRotatef((i - 1) * 90, 0, 1, 0);
                    } else if (gridLight.hasGridLightNeighbour(i) && gridLight.hasGridLightNeighbour(j) && side == 5) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    }
                }
                else if (gridLight.neighbourCount() == 3)
                {
                    if (gridLight.hasGridLightNeighbour(i) == false && (side == 1 || side == 3)) {
                        GL11.glRotatef((i - 1) * 90, 0, 1, 0);
                    }
                    else if (gridLight.hasGridLightNeighbour(i) == false && side == 0) {
                        GL11.glRotatef((3-i) * 90, 0, 1, 0);
                    }
                    else if (gridLight.hasGridLightNeighbour(i) == false && (side == 4 || side == 2)) {
                        GL11.glRotatef((-2+i) * 90, 0, 1, 0);
                    }
                    else if (gridLight.hasGridLightNeighbour(i) == false && side == 5) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    }
                }
            }
        }
        if (gridLight.neighbourCount() == 2 && gridLight.neighboursAreClose() == false)
        {
            if (gridLight.hasGridLightNeighbour(2)  && (side == 4 || side == 5 || side == 2))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
            else if (gridLight.hasGridLightNeighbour(1) && (side == 3 || side == 0 || side == 1))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
        }
    }

    private void renderModel(TileEntityGridLight gridLight)
    {
        if (gridLight.neighbourCount() == 1) {
            this.modelCon1.renderModel(0.0625f);
        } else if ((gridLight.neighbourCount() == 2) && gridLight.neighboursAreClose()){
            this.modelCon2a.renderModel(0.0625f);
        }else if ((gridLight.neighbourCount() == 2) && gridLight.neighboursAreClose() == false){
            this.modelCon2b.renderModel(0.0625f);
        } else if (gridLight.neighbourCount() == 3) {
            this.modelCon3.renderModel(0.0625f);
        } else if (gridLight.neighbourCount() == 4) {
            this.modelCon4.renderModel(0.0625f);
        } else {
            this.model.renderModel(0.0625f);
        }
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_)
    {
        TileEntityGridLight gridLight = (TileEntityGridLight)(entity);
        GL11.glPushMatrix();
        alignTileEntityAccordingMetadata(x, y, z, gridLight.blockMetadata);
        rotateTilEntityAccordingNBT(gridLight, gridLight.getBlockMetadata());
        this.bindTexture(texture);
            GL11.glPushMatrix();
            renderModel(gridLight);
            GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

package de.schimski.bulbs.renderer;

import assets.bulbs.models.*;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityThinLight;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererThinLight extends TileEntitySpecialRenderer{

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] thinLightTypes = new String[] {"thinLightX2", "thinLightBlack", "thinLightRed", "thinLightGreen", "thinLightBrown", "thinLightBlue", "thinLightPurple", "thinLightCyan", "thinLightLightGray", "thinLightGray", "thinLightPink", "thinLightLimeGreen", "thinLightYellow", "thinLightLightBlue", "thinLightMagenta", "thinLightOrange", "thinLightWhite"};
    private ModelThinLight model;
    private ModelThinLightCon1Con2b modelCon1Con2b;
    private ModelThinLightCon2a modelCon2a;
    private ModelThinLightCon3 modelCon3;
    private ModelThinLightCon4 modelCon4;

    static int myRenderID;



    public RendererThinLight() {
        this.model = new ModelThinLight();
        this.modelCon1Con2b = new ModelThinLightCon1Con2b();
        this.modelCon2a = new ModelThinLightCon2a();
        this.modelCon3 = new ModelThinLightCon3();
        this.modelCon4 = new ModelThinLightCon4();


        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/" + thinLightTypes[i] + ".png");
        }
    }

    private void alignTileEntityAccordingState(double x, double y, double z, int state)
    {
        if (state == 0) {
            GL11.glTranslatef((float)x + 0.5f, (float)y - 0.5f, (float)z + 0.5f);
        } else if (state == 1) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
            GL11.glRotatef(180, 1, 0, 0);
        } else if (state == 2) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z - 0.5f);
            GL11.glRotatef(90, 0, -1, 0);
            GL11.glRotatef(90, 0, 0, -1);
        } else if (state == 3) {
            GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 1.5f);
            GL11.glRotatef(90, -1, 0, 0);
        } else if (state == 4) {
            GL11.glTranslatef((float)x - 0.5f, (float)y + 0.5f, (float)z + 0.5f);
            GL11.glRotatef(90, 0, 0, -1);
        } else if (state == 5) {
            GL11.glTranslatef((float)x + 1.5f, (float)y + 0.5f, (float)z + 0.5f);;
            GL11.glRotatef(90, 0, 0, 1);
        }
    }

    private void rotateTilEntityAccordingNBT(TileEntityThinLight thinLight, int side)
    {
        if (thinLight.neighbourCount() == 1 || thinLight.neighboursAreClose() || thinLight.neighbourCount() == 3) {
            for (int i = 0; i < 4; i++) {
                if (thinLight.neighbourCount() == 1) {
                    if (thinLight.hasConnectingLightNeighbour(i) && side == 0) {
                        GL11.glRotatef((2-i) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 1) {
                        GL11.glRotatef((i-2) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 2) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 3) {
                        GL11.glRotatef((i-2) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 4) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && side == 5) {
                        GL11.glRotatef((i - 1) * 90, 0, 1, 0);
                    }
                } else if (thinLight.neighbourCount() == 2 && thinLight.neighboursAreClose()) {
                    int j = (i < 3) ? i + 1 : 0;
                    if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 0) {
                        GL11.glRotatef((2- i) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 1) {
                        GL11.glRotatef((i-1) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 2) {
                        GL11.glRotatef(((i-2) * 90), 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 3) {
                        GL11.glRotatef(((i-1) * 90), 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 4) {
                        GL11.glRotatef((i - 2) * 90, 0, 1, 0);
                    } else if (thinLight.hasConnectingLightNeighbour(i) && thinLight.hasConnectingLightNeighbour(j) && side == 5) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    }
                }
                else if (thinLight.neighbourCount() == 3)
                {
                    if (thinLight.hasConnectingLightNeighbour(i) == false && (side == 1 || side == 3)) {
                        GL11.glRotatef((i + 1) * 90, 0, 1, 0);
                    }
                    else if (thinLight.hasConnectingLightNeighbour(i) == false && side == 0) {
                        GL11.glRotatef((1-i) * 90, 0, 1, 0);
                    }
                    else if (thinLight.hasConnectingLightNeighbour(i) == false && (side == 4 || side == 2)) {
                        GL11.glRotatef(i * 90, 0, 1, 0);
                    }
                    else if (thinLight.hasConnectingLightNeighbour(i) == false && side == 5) {
                        GL11.glRotatef((i + 2) * 90, 0, 1, 0);
                    }
                }
            }
        }
        if (thinLight.neighbourCount() == 2 && thinLight.neighboursAreClose() == false)
        {
            if (thinLight.hasConnectingLightNeighbour(2)  && (side == 4 || side == 5 || side == 2))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
            else if (thinLight.hasConnectingLightNeighbour(1) && (side == 3 || side == 0 || side == 1))
            {
                GL11.glRotatef(90, 0, 1, 0);
            }
        }
    }

    private void renderModel(TileEntityThinLight thinLight)
    {
        if (thinLight.neighbourCount() == 1) {
            this.modelCon1Con2b.renderModel(0.0625f);
        } else if ((thinLight.neighbourCount() == 2) && thinLight.neighboursAreClose()){
            this.modelCon2a.renderModel(0.0625f);
        }else if ((thinLight.neighbourCount() == 2) && !thinLight.neighboursAreClose()){
            this.modelCon1Con2b.renderModel(0.0625f);
        } else if (thinLight.neighbourCount() == 3) {
            this.modelCon3.renderModel(0.0625f);
        } else if (thinLight.neighbourCount() == 4) {
            this.modelCon4.renderModel(0.0625f);
        } else {
            this.model.renderModel(0.0625f);
        }
    }
    
    private void selectAndBindTexture(TileEntityThinLight thinLight) {
        int textureIndex = 0;
        ItemStack stack = thinLight.getStackInSlot(0);
        if (stack != null) {
            textureIndex = stack.getItemDamage()+1;
        }
        this.bindTexture(texture[textureIndex]);
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_)
    {
        TileEntityThinLight thinLight = (TileEntityThinLight)(entity);
        GL11.glPushMatrix();
        alignTileEntityAccordingState(x, y, z, thinLight.getState());
        rotateTilEntityAccordingNBT(thinLight, thinLight.getState());
        selectAndBindTexture(thinLight);
            GL11.glPushMatrix();
            renderModel(thinLight);
            GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}

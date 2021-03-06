package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelBlockLightX32.ModelBlockLightX32;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.item.ItemBulbRainbow;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityBlockLight;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererBlockLight extends TileEntitySpecialRenderer{

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] blockLightTypes = new String[] {"blockLight", "blockLightBlack", "blockLightRed", "blockLightGreen", "blockLightBrown", "blockLightBlue", "blockLightPurple", "blockLightCyan", "blockLightLightGray", "blockLightGray", "blockLightPink", "blockLightLimeGreen", "blockLightYellow", "blockLightLightBlue", "blockLightMagenta", "blockLightOrange", "blockLightWhite"};

    private ModelBlockLightX32 modelBlockLight;

    private float renderScale = 0.03125f; //0.03125f; //0.0625f
    private String smoothTextures;

    public RendererBlockLight() {
        modelBlockLight = new ModelBlockLightX32();
        smoothTextures = ConfigurationHandler.smoothTextures ? "" : "Noise";

        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/blockLight/" + blockLightTypes[i] + smoothTextures + "X32.png");
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

    private void rotateTilEntityAccordingNBT(TileEntityBlockLight blockLight)
    {
        GL11.glRotatef(((blockLight.getRotation()-2)/2) * 90, 0, 1, 0);
    }

    private void renderModel() {
        modelBlockLight.renderModel(renderScale);
    }

    private void renderModelAlpha( ) {
        modelBlockLight.renderAlpha(renderScale);
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

    private void renderBlockLightSub0(boolean glow) {

        if (ClientProxy.renderPass == 0) {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
            renderModel();
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            renderModelAlpha();
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    private void renderBlockLightSub1(boolean glow) {

        float t =  (float)1/192;
        float p = (float)1/32;
        int x = glow ? 1 : 0;
        GL11.glScalef(p, p, p);
        GL11.glTranslatef(-16, 24, -16);
        GL11.glPushMatrix();

        if (ClientProxy.renderPass == 1) {
            GL11.glEnable(GL11.GL_BLEND);

            //Bottom
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0,64*t);
                GL11.glVertex3f(-x,x,-x);

                GL11.glTexCoord2f(0,136*t);
                GL11.glVertex3f(-x,x,32+x);

                GL11.glTexCoord2f(72*t,136*t);
                GL11.glVertex3f(32+x,x,32+x);

                GL11.glTexCoord2f(72*t,72*t);
                GL11.glVertex3f(32+x,x,-x);

            GL11.glEnd();

            //Top
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0,64*t);
                GL11.glVertex3f(-x,-32-x,-x);

                GL11.glTexCoord2f(72*t,72*t);
                GL11.glVertex3f(32+x,x,-x);

                GL11.glTexCoord2f(72*t,136*t);
                GL11.glVertex3f(32+x,x,32+x);

                GL11.glTexCoord2f(0,136*t);
                GL11.glVertex3f(-x,-32-x,32+x);
            GL11.glEnd();

            //Side Quad
            GL11.glBegin(GL11.GL_QUADS);
                GL11.glTexCoord2f(0,64*t);
                GL11.glVertex3f(-x,x,-x);

                GL11.glTexCoord2f(72*t,72*t);
                GL11.glVertex3f(-x,-32-x,-x);

                GL11.glTexCoord2f(72*t,136*t);
                GL11.glVertex3f(-x,-32-x,32+x);

                GL11.glTexCoord2f(0,136*t);
                GL11.glVertex3f(-x,x,32+x);
            GL11.glEnd();

            //Side Triangle
            GL11.glBegin(GL11.GL_POLYGON);
                GL11.glTexCoord2f(0,64*t);
                GL11.glVertex3f(-x,x,-x);

                GL11.glTexCoord2f(72*t,72*t);
                GL11.glVertex3f(32+x,x,-x);

                GL11.glTexCoord2f(72*t,136*t);
                GL11.glVertex3f(-x,-32-x,-x);
            GL11.glEnd();

            //Side Triangle
            GL11.glBegin(GL11.GL_POLYGON);
                GL11.glTexCoord2f(0,64*t);
                GL11.glVertex3f(-x,x,32+x);

                GL11.glTexCoord2f(72*t,136*t);
                GL11.glVertex3f(-x,-32-x,32+x);

                GL11.glTexCoord2f(72*t,72*t);
                GL11.glVertex3f(32+x,x,32+x);
            GL11.glEnd();

        GL11.glDisable(GL11.GL_BLEND);
        } else {

            //Bottom
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0,64*t);
            GL11.glVertex3f(2,-2,2);

            GL11.glTexCoord2f(0,136*t);
            GL11.glVertex3f(2,-2,30);

            GL11.glTexCoord2f(72*t,136*t);
            GL11.glVertex3f(27,-2,30);

            GL11.glTexCoord2f(72*t,72*t);
            GL11.glVertex3f(27,-2,2);

            GL11.glEnd();

            //Top
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0,64*t);
            GL11.glVertex3f(2,-27,2);

            GL11.glTexCoord2f(72*t,72*t);
            GL11.glVertex3f(27,-2,2);

            GL11.glTexCoord2f(72*t,136*t);
            GL11.glVertex3f(27,-2,30);

            GL11.glTexCoord2f(0,136*t);
            GL11.glVertex3f(2,-27,30);
            GL11.glEnd();

            //Side Quad
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0,64*t);
            GL11.glVertex3f(2,-2,2);

            GL11.glTexCoord2f(72*t,72*t);
            GL11.glVertex3f(2,-27,2);

            GL11.glTexCoord2f(72*t,136*t);
            GL11.glVertex3f(2,-27,30);

            GL11.glTexCoord2f(0,136*t);
            GL11.glVertex3f(2,-2,30);
            GL11.glEnd();

            //Side Triangle
            GL11.glBegin(GL11.GL_POLYGON);
            GL11.glTexCoord2f(0,64*t);
            GL11.glVertex3f(2,-2,2);

            GL11.glTexCoord2f(72*t,72*t);
            GL11.glVertex3f(27,-2,2);

            GL11.glTexCoord2f(72*t,136*t);
            GL11.glVertex3f(2,-27,2);
            GL11.glEnd();

            //Side Triangle
            GL11.glBegin(GL11.GL_POLYGON);
            GL11.glTexCoord2f(0,64*t);
            GL11.glVertex3f(2,-2,30);

            GL11.glTexCoord2f(72*t,136*t);
            GL11.glVertex3f(2,-27,30);

            GL11.glTexCoord2f(72*t,72*t);
            GL11.glVertex3f(27,-2,30);
            GL11.glEnd();

        }
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_)
    {
        TileEntityBlockLight blockLight = (TileEntityBlockLight)(entity);

        GL11.glPushMatrix();
            alignTileEntityAccordingState(x, y, z, blockLight.getState());
            rotateTilEntityAccordingNBT(blockLight);
            selectAndBindTexture(blockLight);
            int rotation = blockLight.getRotation();
            if (rotation < 2) {
                renderBlockLightSub0(rotation==0 ? true : false);
            } else if (rotation < 10) {
                renderBlockLightSub1(((rotation-2) % 2) == 0 ? true : false);
            }

        GL11.glPopMatrix();
    }
}

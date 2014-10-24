package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelPoleLightX32.ModelPoleLightX32;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityPoleLight;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererPoleLight extends TileEntitySpecialRenderer{

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] poleLightTypes = new String[] {"poleLight", "poleLightBlack", "poleLightRed", "poleLightGreen", "poleLightBrown", "poleLightBlue", "poleLightPurple", "poleLightCyan", "poleLightLightGray", "poleLightGray", "poleLightPink", "poleLightLimeGreen", "poleLightYellow", "poleLightLightBlue", "poleLightMagenta", "poleLightOrange", "poleLightWhite"};

    private ModelPoleLightX32 modelPoleLight0ConX32;
    private ModelPoleLightX32 modelPoleLight1ConX32;
    private ModelPoleLightX32 modelPoleLight2ConX32;

    private float renderScale = 0.03125f; //0.0625f

    public RendererPoleLight() {
        modelPoleLight0ConX32 = new ModelPoleLightX32(30, -31, 0);
        modelPoleLight1ConX32 = new ModelPoleLightX32(31, -32, 24);
        modelPoleLight2ConX32 = new ModelPoleLightX32(32, -32, 16);

        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/poleLight/" + poleLightTypes[i] + "X32.png");
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

    private void rotateTilEntityAccordingNBT(TileEntityPoleLight poleLight)
    {
        if (poleLight.getState() == 2 && poleLight.neighbourCount() == 1 && poleLight.hasConnectingLightNeighbour(0)) {
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glTranslatef(0, -0.5f, 0);
        } else if (poleLight.getState() == 0 && poleLight.neighbourCount() == 1 && poleLight.hasConnectingLightNeighbour(0)) {
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glTranslatef(0, -0.5f, 0);
        } else if (poleLight.getState() == 4 && poleLight.neighbourCount() == 1 && poleLight.hasConnectingLightNeighbour(0)) {
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glTranslatef(0, -0.5f, 0);
        }

        //GL11.glRotatef(poleLight.getRotation(), 0, 1, 0);
    }


    private void renderModel(TileEntityPoleLight poleLight) {

        if (poleLight.neighbourCount() == 0) {
            modelPoleLight0ConX32.renderModel(renderScale);
        } else if (poleLight.neighbourCount() == 1) {
            modelPoleLight1ConX32.renderModel(renderScale);
        } else {
            modelPoleLight2ConX32.renderModel(renderScale);
        }
    }

    private void renderModelAlpha(TileEntityPoleLight poleLight) {

        if (poleLight.neighbourCount() == 0) {
            modelPoleLight0ConX32.renderAlpha(renderScale);
        } else if (poleLight.neighbourCount() == 1) {
            modelPoleLight1ConX32.renderAlpha(renderScale);
        } else {
            modelPoleLight2ConX32.renderAlpha(renderScale);
        }
    }
    
    private void selectAndBindTexture(TileEntityPoleLight poleLight) {
        int textureIndex = 0;
        ItemStack stack = poleLight.getStackInSlot(0);
        if (stack != null) {
            textureIndex = stack.getItemDamage()+1;
        }

        bindTexture(texture[textureIndex]);
    }

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
        TileEntityPoleLight poleLight = (TileEntityPoleLight)(entity);

        GL11.glPushMatrix();
            alignTileEntityAccordingState(x, y, z, poleLight.getState());
            rotateTilEntityAccordingNBT(poleLight);
            selectAndBindTexture(poleLight);

        if (ClientProxy.renderPass == 0) {

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
            renderModel(poleLight);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();
        } else {

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            renderModelAlpha(poleLight);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}

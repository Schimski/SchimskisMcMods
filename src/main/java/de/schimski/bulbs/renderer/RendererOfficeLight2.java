package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelOfficeLight2X32.ModelOfficeLight2X32;
import de.schimski.bulbs.item.ItemBulbRainbow;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight2;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererOfficeLight2 extends TileEntitySpecialRenderer{

    private static final ResourceLocation[] texture = new ResourceLocation[17];
    public static final String[] officeLight2Types = new String[] {"officeLight2", "officeLight2Black", "officeLight2Red", "officeLight2Green", "officeLight2Brown", "officeLight2Blue", "officeLight2Purple", "officeLight2Cyan", "officeLight2LightGray", "officeLight2Gray", "officeLight2Pink", "officeLight2LimeGreen", "officeLight2Yellow", "officeLight2LightBlue", "officeLight2Magenta", "officeLight2Orange", "officeLight2White"};

    private ModelOfficeLight2X32 modelOfficeLight2;

    private float renderScale = 0.03125f; //0.0625f

    public RendererOfficeLight2() {
        modelOfficeLight2 = new ModelOfficeLight2X32();

        for (int i = 0; i<17; i++) {
            texture[i] = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/officeLight2/" + officeLight2Types[i] + "X32.png");
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

    private void rotateTilEntityAccordingNBT(TileEntityOfficeLight2 officeLight2)
    {
        GL11.glRotatef(officeLight2.getRotation(), 0, 1, 0);
    }

    private void renderModel() {
        modelOfficeLight2.renderModel(renderScale);
    }

    private void renderModelAlpha( ) {

        modelOfficeLight2.renderAlpha(renderScale);
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
        TileEntityOfficeLight2 officeLight2 = (TileEntityOfficeLight2)(entity);

        GL11.glPushMatrix();
            alignTileEntityAccordingState(x, y, z, officeLight2.getState());
            rotateTilEntityAccordingNBT(officeLight2);
            selectAndBindTexture(officeLight2);

            if (ClientProxy.renderPass == 0) {
                GL11.glPushMatrix();
    //                GL11.glDisable(GL11.GL_CULL_FACE);
                    renderModel();
    //                GL11.glEnable(GL11.GL_CULL_FACE);
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
        GL11.glPopMatrix();
    }
}

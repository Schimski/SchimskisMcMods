package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelOfficeLight1.ModelOfficeLight1;
import assets.bulbs.models.ModelPoleLightX32.ModelPoleLightX32;
import de.schimski.bulbs.client.handler.KeyInputEventHandler;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RendererItemPoleLight implements IItemRenderer {

    protected ModelPoleLightX32 blockModel;
    protected ResourceLocation blockTexture;
    TileEntitySpecialRenderer render;

    public RendererItemPoleLight() {
        blockModel = new ModelPoleLightX32(0);
        blockTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/poleLight/poleLightWhiteX32.png");
        //render = new RendererOfficeLight1();
        render = new RendererPoleLight();


    }
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type) {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case ENTITY:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
                                         ItemRendererHelper helper) {
        switch (type) {
            case ENTITY: {
                return false;
            }
            case EQUIPPED: {
                return false;
            }
            case EQUIPPED_FIRST_PERSON: {
                return false;
            }
            case INVENTORY:// this case statement is required to get an inventory texture
            {
                return helper == ItemRendererHelper.INVENTORY_BLOCK;
            }
            default: {
                return false;
            }
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case EQUIPPED:
                GL11.glPushMatrix();
                // rotates the item
                GL11.glRotatef(-177, 1, 0, 0);
                GL11.glRotatef(-191, 0, 1, 0);
                GL11.glRotatef(-167, 0, 0, 1);
                GL11.glTranslatef(0.4f, 0, -0.1f);
                GL11.glScalef(2F, 2F, 2F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item

                GL11.glPushMatrix();
                blockModel.renderModel(0.02f);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_BLEND);
                blockModel.renderAlpha(0.02f);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();

                GL11.glPopMatrix();
                break;
            // Renders the Campfire on ground as a pickable item
            case EQUIPPED_FIRST_PERSON:

                GL11.glPushMatrix();
                // rotates the item
                /*GL11.glRotatef(KeyInputEventHandler.rotX, 1, 0, 0);
                GL11.glRotatef(KeyInputEventHandler.rotY, 0, 1, 0);
                GL11.glRotatef(KeyInputEventHandler.rotZ, 0, 0, 1);
                GL11.glTranslatef(KeyInputEventHandler.mX, KeyInputEventHandler.mY, KeyInputEventHandler.mZ);*/

                GL11.glRotatef(-174, 1, 0, 0);
                GL11.glRotatef(80, 0, 1, 0);
                GL11.glRotatef(-22, 0, 0, 1);
                GL11.glTranslatef(6, 0, 5);

                GL11.glScalef(8F, 8F, 8F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item

                GL11.glPushMatrix();
                blockModel.renderModel(0.02f);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_BLEND);
                blockModel.renderAlpha(0.02f);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();

                GL11.glPopMatrix();
                break;

            default:
                break;
            // Renders the Campfire on ground as a pickable item
        }
        switch (type) {
            case ENTITY:

                GL11.glPushMatrix();
                // rotates the item and translates the item
                GL11.glRotatef(0, 0, 0, 1);
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glRotatef(180, 1, 0, 0);
                GL11.glTranslatef(0, -0.8f, 0f);
                GL11.glScalef(1F, 1F, 1F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item
                GL11.glPushMatrix();

                blockModel.renderModel(0.02f);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);

                blockModel.renderAlpha(0.02f);
                GL11.glPopMatrix();
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            default:
                break;
        }
        switch (type) {
            case INVENTORY:

                GL11.glPushMatrix();
                // rotates the item and translates the item
                GL11.glRotatef(0, 0, 0, 1);
                GL11.glRotatef(0, 0, 1, 0);
                GL11.glRotatef(180, 1, 0, 0);
                GL11.glTranslatef(0f, -0.3f, 0f);
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item


                    GL11.glPushMatrix();
                    blockModel.renderModel(0.02f);
                    GL11.glPopMatrix();

                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    blockModel.renderAlpha(0.02f);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glPopMatrix();
                GL11.glPopMatrix();
            default:
                break;
        }

    }
/*    protected ResourceLocation getEntityTexture(Entity Campfire) {
        return this.blockTexture;
    }*/
}


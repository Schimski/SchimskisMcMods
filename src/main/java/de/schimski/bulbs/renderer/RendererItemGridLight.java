package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelGridLightX32.ModelGridLightX32;
import de.schimski.bulbs.client.handler.KeyInputEventHandler;
import de.schimski.bulbs.client.settings.Keybindings;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RendererItemGridLight implements IItemRenderer {

    protected ModelGridLightX32 blockModel;
    protected ResourceLocation blockTexture;
    TileEntitySpecialRenderer render;

    public RendererItemGridLight() {
        blockModel = new ModelGridLightX32();
        blockTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/gridLight/gridLightWhiteX32.png");
        render = new RendererThinLight();

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
                GL11.glRotatef(90, 0, 0, 1);
                GL11.glRotatef(0, 0, 1, 0);
                GL11.glRotatef(-75, 1, 0, 0);
                GL11.glTranslatef(0.5f, 0.5f, -0.4f);
                GL11.glScalef(-1.5F, -1.5F, -1.5F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item

                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_CULL_FACE);
                blockModel.renderModel(0.02f);
                GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                blockModel.renderAlpha(0.02f);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();

                GL11.glPopMatrix();

                break;

            case EQUIPPED_FIRST_PERSON:
            GL11.glPushMatrix();
                // rotates the item
                GL11.glRotatef(-149, 0, 0, 1);
                GL11.glRotatef(-231, 0, 1, 0);
                GL11.glRotatef(-186, 1, 0, 0);
                GL11.glTranslatef(0, 0, 6);
                GL11.glScalef(-8F, -8F, -8F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item

                GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    blockModel.renderModel(0.02f);
                    GL11.glEnable(GL11.GL_CULL_FACE);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    blockModel.renderAlpha(0.02f);
                    GL11.glDisable(GL11.GL_BLEND);
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
                GL11.glTranslatef(0, -1.2f, 0f);
                GL11.glScalef(2.5F, 2.5F, 2.5F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item
                GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    blockModel.renderModel(0.02f);
                    GL11.glEnable(GL11.GL_CULL_FACE);
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
        switch (type) {
            case INVENTORY:

                GL11.glPushMatrix();
                // rotates the item and translates the item
                GL11.glRotatef(0, 0, 0, 1);
                GL11.glRotatef(0, 0, 1, 0);
                GL11.glRotatef(180, 1, 0, 0);
                GL11.glTranslatef(0f, -1f, 0f);
                GL11.glScalef(2F, 2F, 2F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item
                GL11.glPushMatrix();
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    blockModel.renderModel(0.02f);
                    GL11.glEnable(GL11.GL_CULL_FACE);
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


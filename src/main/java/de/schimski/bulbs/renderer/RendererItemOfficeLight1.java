package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelOfficeLight1.ModelOfficeLight1;
import assets.bulbs.models.ModelThinLightX32.ModelThinLightX32;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RendererItemOfficeLight1 implements IItemRenderer {

    protected ModelOfficeLight1 blockModel;
    protected ResourceLocation blockTexture;
    TileEntitySpecialRenderer render;

    public RendererItemOfficeLight1() {
        blockModel = new ModelOfficeLight1();
        blockTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/officeLight1/officeLight1WhiteX32.png");
        render = new RendererOfficeLight1();

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
                GL11.glRotatef(150, 0, 0, 1);
                GL11.glRotatef(-35, 0, 1, 0);
                GL11.glRotatef(-115, 1, 0, 0);
                GL11.glTranslatef(0.0f, 0.1f, -0.6f);
                GL11.glScalef(-1F, -1F, -1F);
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
                GL11.glRotatef(150, 0, 0, 1);
                GL11.glRotatef(-85, 0, 1, 0);
                GL11.glRotatef(-80, 1, 0, 0);
                GL11.glTranslatef(0.0f, 0.1f, -1.6f);
                GL11.glScalef(-1F, -1F, -1F);
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


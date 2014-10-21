package de.schimski.bulbs.renderer;

import assets.bulbs.models.ModelGridLight;
import assets.bulbs.models.ModelThinLightX32;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RendererItemGridLight implements IItemRenderer {

    protected ModelGridLight blockModel;
    protected ResourceLocation blockTexture;
    TileEntitySpecialRenderer render;

    public RendererItemGridLight() {
        blockModel = new ModelGridLight();
        blockTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/models/gridLight/gridLightCyan.png");
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
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                // rotates the item
                GL11.glRotatef(90, 0, 0, 1);
                GL11.glRotatef(0, 0, 1, 0);
                GL11.glRotatef(-90, 1, 0, 0);
                GL11.glTranslatef(0.5f, 1.5f,-0.9f);
                GL11.glScalef(-3F, -3F, -3F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item

                GL11.glPushMatrix();

                blockModel.renderModel(0.02f);
                GL11.glPopMatrix();

                /*GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);

                blockModel.renderAlpha(0.02f);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();*/

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
                GL11.glTranslatef(0, -1.2f, 0f);
                GL11.glScalef(2.5F, 2.5F, 2.5F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item
                GL11.glPushMatrix();

                blockModel.renderModel(0.02f);
                GL11.glPopMatrix();

                /*GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);

                blockModel.renderAlpha(0.02f);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();*/

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
                GL11.glTranslatef(0f, -2f, 0f);
                GL11.glScalef(4F, 4F, 4F);
                Minecraft.getMinecraft().renderEngine
                        .bindTexture(this.blockTexture);
                // renders the item
                GL11.glPushMatrix();

                blockModel.renderModel(0.02f);
                GL11.glPopMatrix();

                /*GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);

                blockModel.renderAlpha(0.02f);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();*/

                GL11.glPopMatrix();
            default:
                break;
        }

    }
/*    protected ResourceLocation getEntityTexture(Entity Campfire) {
        return this.blockTexture;
    }*/
}


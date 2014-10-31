package de.schimski.bulbs.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.tileEntity.TileEntityStealthLight;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RendererStealthLight implements ISimpleBlockRenderingHandler{

        public static int renderPass;
        IIcon icon;
        static IIcon stone;
        @Override
        public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {/*
            Tessellator tessellator = Tessellator.instance;
            block.setBlockBoundsForItemRender();
            renderer.setRenderBoundsFromBlock(block);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);*/
        }
        @Override
        public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

            renderer.renderStandardBlock(((TileEntityStealthLight)world.getTileEntity(x,y,z)).getBlock(), x, y, z);




            return true;
            /*if(modelId == VoidMod.render){
                if(block instanceof BlockNullOre){
                    System.out.println("isBlock");
                    getOreTexture((BlockNullOre) block, world, x, y, z);
                    if(renderPass == 0){
                        //renderer.setOverrideBlockTexture(stone);
                        //renderer.renderStandardBlock(block, x, y, z);
                        //renderer.clearOverrideBlockTexture();
                        renderer.renderStandardBlock(VoidMod.NullOre, x, y, z);
                    }
                    else{
                        if(stone == null){
                            renderer.setOverrideBlockTexture(stone);
                            renderer.renderStandardBlock(block, x, y, z);
                            renderer.clearOverrideBlockTexture();
                        }
                        else{
                            drawStone(block, renderer);
                        }
                    }
                    return true;
                }
                else{
                    renderer.renderStandardBlock(block, x, y, z);
                }
            }
            return false;*/
        }

        @Override
        public int getRenderId() {
            // TODO Auto-generated method stub
            return ClientProxy.stealthLightRenderType;

        }

        private static void drawStone(Block block, RenderBlocks renderer){
            renderer.setRenderBoundsFromBlock(block);
            renderer.renderFaceYNeg(block, 0.1D, 0.1D, 0.1D, stone);
            renderer.renderFaceYPos(block, 0.1D, 0.1D, 0.1D, stone);
            renderer.renderFaceZNeg(block, 0.1D, 0.1D, 0.1D, stone);
            renderer.renderFaceZPos(block, 0.1D, 0.1D, 0.1D, stone);
            renderer.renderFaceXNeg(block, 0.1D, 0.1D, 0.1D, stone);
            renderer.renderFaceXPos(block, 0.1D, 0.1D, 0.1D, stone);
        }
        @Override
        public boolean shouldRender3DInInventory(int modelId) {
            // TODO Auto-generated method stub
            return true;
        }

    }

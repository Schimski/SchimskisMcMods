package de.schimski.bulbs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityBlockLight;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.tileEntity.TileEntityPoleLight;
import de.schimski.bulbs.tileEntity.TileEntityStealthLight;
import de.schimski.bulbs.utility.BlockHelper;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockStealthLight extends BlockBulbsContainer {

    @SideOnly(Side.CLIENT)
    private int blockMeta=0;
    private boolean isOpaque=true;
    private IIcon standardIcon;

    public BlockStealthLight() {
        super(Material.rock);
        this.setBlockName("stealthLight");
        this.setHardness(0.5f);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
        if (ClientProxy.screwDriverSelected) {
            return standardIcon;
        } else {
            return ((TileEntityStealthLight)block.getTileEntity(x,y,z)).getIcon(side);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.standardIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "blockStealthLight");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {

        if (player.getHeldItem() != null) {
            Item item = player.getHeldItem().getItem();
            if (item instanceof ItemBlock) {

                blockMeta = item.getDamage(player.getHeldItem());
                Block block = Block.getBlockFromItem(item);
//                blockClass = block.getUnlocalizedName();
//                isOpaque = block.isOpaqueCube();
                ((TileEntityStealthLight)world.getTileEntity(x,y,z)).saveStealthStack(player.getHeldItem());
                world.notifyBlockChange(x,y,z,this);
                world.markBlockRangeForRenderUpdate(x-1,y-1,z-1,x+1, y+1, z+1);
                return true;
            }
        }
        super.onBlockActivated(world, x,y,z, player, p_149727_6_,p_149727_7_,p_149727_8_,p_149727_9_);
        return false;
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        return side;
    }

/*
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        if (blockClass.equals("tile.grass")) {
            double d0 = 0.5D;
            double d1 = 1.0D;
            return ColorizerGrass.getGrassColor(d0, d1);
        }
        return 16777215;
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     *
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        if (blockClass.equals("tile.leaves")) {
            return (p_149741_1_ & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((p_149741_1_ & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
        } else if (blockClass.equals("tile.grass")) {
            return getBlockColor();
        }
        return 16777215;
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     *
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        if (blockClass.equals("tile.leaves")) {
            int l = blockMeta;
            return (l & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((l & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : colorMultiplier2(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_));
        } else if(blockClass.equals("tile.grass")) {
            return colorMultiplier2(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_);
        }
        return 16777215;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier2(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1)
        {
            for (int l1 = -1; l1 <= 1; ++l1)
            {
                int i2 = p_149720_1_.getBiomeGenForCoords(p_149720_2_ + l1, p_149720_4_ + k1).getBiomeGrassColor(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
                l += (i2 & 16711680) >> 16;
                i1 += (i2 & 65280) >> 8;
                j1 += i2 & 255;
            }
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }*/

    @Override
    public int getRenderType() {
        return ClientProxy.stealthLightRenderType;
    }

    @Override
    public boolean isOpaqueCube() {
        return isOpaque;
    }

    public boolean renderAsNormalBlock()
    {
        return true;
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        TileEntityStealthLight stealthLight = new TileEntityStealthLight(metadata);
        passNeighboursToTileEntity(stealthLight);
        return stealthLight;
    }
}
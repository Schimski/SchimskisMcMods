package de.schimski.bulbs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.utility.BlockHelper;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockGridLight extends BlockBulbs{

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon;

    public BlockGridLight()
    {
        super();
        this.setBlockName("gridLight");
        this.setLightLevel(1f);
        this.setHardness(0.5f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int p_149691_2_)
    {
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon(Reference.MOD_ID + ":" + "itemGridLight");
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int x, int y, int z)
    {
        // Sets the bounds of the block.  minX, minY, minZ, maxX, maxY, maxZ
        float p = 1/16f;
        switch (p_149719_1_.getBlockMetadata(x,y,z)) {
            case 0:
                this.setBlockBounds(2*p, 14*p, 2*p, 14*p, 16*p, 14*p);
                break;
            case 1:
                this.setBlockBounds(2*p, 0, 2*p, 14*p, 3*p, 14*p);
                break;
            case 2:
                this.setBlockBounds(2*p, 2*p, 13*p, 14*p, 14*p, 16*p);
                break;
            case 3:
                this.setBlockBounds(2*p, 2*p, 0*p, 14*p, 14*p, 3*p);
                break;
            case 4:
                this.setBlockBounds(13*p, 2*p, 2*p, 16*p, 14*p, 14*p);
                break;
            case 5:
                this.setBlockBounds(0*p, 2*p, 2*p, 3*p, 14*p, 14*p);
                break;
        }
    }


    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
    {
        boolean[] check = BlockHelper.compareBlocks4Sides(p_149660_1_,p_149660_2_,p_149660_3_,p_149660_4_, "tile.bulbs:gridLight", p_149660_5_);
        LogHelper.info(check[0] + " - " + check[1] + " - " + check[2] + " - " + check[3]);

        return p_149660_5_;
    }

    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
    {
        if (p_149699_5_.getHeldItem() != null) {
            LogHelper.info(p_149699_5_.getHeldItem().getDisplayName());
        }
    }

    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_5_.getHeldItem() != null) {
            LogHelper.info(p_149727_5_.getHeldItem().getDisplayName());
        }
        return false;
    }

    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityGridLight(metadata);
    }
}

package de.schimski.bulbs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight1;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight2;
import de.schimski.bulbs.utility.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockOfficeLight2 extends BlockBulbsContainer{

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon;

    public BlockOfficeLight2()
    {
        super();
        this.setBlockName("officeLight2");
        this.setHardness(0.5f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int p_149691_2_)
    {
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "itemGridLight");
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        connectNeighbours = BlockHelper.compareBlocks4Sides(world, x, y, z, getUnlocalizedName(), (byte) side);
        return side;
    }
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourBlock)
    {
        TileEntityLightContainer entity = (TileEntityLightContainer) world.getTileEntity(x,y,z);
        if (entity != null)
        {
            if (entity instanceof TileEntityOfficeLight1) {
                connectNeighbours = BlockHelper.compareBlocks4Sides(world, x, y, z, "tile.bulbs:officeLight2", entity.getState());
                passNeighboursToTileEntity(entity);
            }

            notifyBlockChange(world, x, y, z, entity);
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */

    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z)
    {/*
        // Sets the bounds of the block.  minX, minY, minZ, maxX, maxY, maxZ
        float p = 1/16f;
        switch (((TileEntityOfficeLight1)block.getTileEntity(x,y,z)).getState()) {
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
        }*/
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

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
        TileEntityOfficeLight2 officeLight2 = new TileEntityOfficeLight2(metadata);
        passNeighboursToTileEntity(officeLight2);
        return officeLight2;
    }
}

package de.schimski.bulbs.block;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.bulbs;
import de.schimski.bulbs.network.messageBulbs;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.utility.BlockHelper;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;


public class BlockGridLight extends BlockBulbsContainer{

    @SideOnly(Side.CLIENT)
    protected IIcon blockIcon;
    private boolean[] connectNeighbours;

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
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + "itemGridLight");
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


    public int onBlockPlaced(World world, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        connectNeighbours = BlockHelper.compareBlocks4Sides(world, x, y, z, "tile.bulbs:gridLight", side);
        LogHelper.info(side);
        return side;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourBlock)
    {
        TileEntity entity = world.getTileEntity(x,y,z);
        if (entity != null)
        {
            if (entity.getClass().getName().equals("de.schimski.bulbs.tileEntity.TileEntityGridLight")) {
                connectNeighbours = BlockHelper.compareBlocks4Sides(world, x, y, z, "tile.bulbs:gridLight", entity.getBlockMetadata());
                passNeighboursToTileEntity((TileEntityGridLight)(entity));
            }
        }
        if (world.isRemote)
        {
            LogHelper.info("ClientMode");
        } else {
            LogHelper.info("ServerMode");
            LogHelper.info("Sending Message to Client");
            bulbs.network.sendToAllAround(new messageBulbs(x+":"+y+":"+z+":"+connectNeighbours[0]+":"+connectNeighbours[1]+":"+connectNeighbours[2]+":"+connectNeighbours[3]), new NetworkRegistry.TargetPoint(world.provider.dimensionId,x,y,z,300));
        }

    }

    public void onPostBlockPlaced(World world, int x, int y, int z, int side)
    {

    }

    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
    {
        if (p_149699_5_.getHeldItem() != null) {
            LogHelper.info(p_149699_5_.getHeldItem().getDisplayName());
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        } else if (player.getHeldItem() == null){
            TileEntityGridLight gridLight = (TileEntityGridLight)tileEntity;
            LogHelper.info("Count: " +gridLight.neighbourCount());
            LogHelper.info(gridLight.hasGridLightNeighbour(0) + " - " + gridLight.hasGridLightNeighbour(1) + " - " + gridLight.hasGridLightNeighbour(2)+ " - "+gridLight.hasGridLightNeighbour(3));
            return false;
        }
        player.openGui(bulbs.instance, 0, world,x,y,z);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    protected void dropInventory(World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory))
        {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.stackSize > 0)
            {
                Random rand = new Random();
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());
                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }
                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
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

    private void passNeighboursToTileEntity(TileEntityGridLight gridLight)
    {
        if (connectNeighbours != null)
        {
            for (int i = 0; i<connectNeighbours.length; i++) {
                gridLight.setNeighbour(i, connectNeighbours[i]);
            }
        }
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        TileEntityGridLight gridLight= new TileEntityGridLight(metadata);
        passNeighboursToTileEntity(gridLight);

        return gridLight;
    }
}

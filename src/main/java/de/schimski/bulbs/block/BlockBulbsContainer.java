package de.schimski.bulbs.block;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.schimski.bulbs.bulbs;
import de.schimski.bulbs.creativetab.CreativeTabBulbs;
import de.schimski.bulbs.handler.GuiHandler;
import de.schimski.bulbs.item.ItemBulbs;
import de.schimski.bulbs.item.ItemScrewDriver;
import de.schimski.bulbs.network.messageBulbs;
import de.schimski.bulbs.reference.Reference;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight1;
import de.schimski.bulbs.tileEntity.TileEntityThinLight;
import de.schimski.bulbs.utility.GuiHelper;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBulbsContainer extends BlockContainer {

    protected boolean[] connectNeighbours={false, false, false, false, false, false};

    public BlockBulbsContainer(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabBulbs.BULBS_TAB);
    }

    public BlockBulbsContainer() {
        this(Material.rock);
   }

    protected void passNeighboursToTileEntity(TileEntityLightContainer bulbsEntity) {
        if (connectNeighbours != null)
        {
            for (int i = 0; i<connectNeighbours.length; i++) {
                bulbsEntity.setNeighbour(i, connectNeighbours[i]);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int metadata = world.getBlockMetadata(x, y, z);
        //LogHelper.info("Metadata: " + metadata);
        this.setLightLevel((float) metadata/15);
        return metadata;
    }

    public void onPostBlockPlaced(World world, int x, int y, int z, int side)
    {
        //TileEntityGridLight gridLight = (TileEntityGridLight) world.getTileEntity(x,y,z);
        //gridLight.updateBlockMetadata();
        //LogHelper.info("PostBLockPlaced");
    }

    public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
    {
        if (p_149699_5_.getHeldItem() != null) {
            LogHelper.info(p_149699_5_.getHeldItem().getDisplayName());
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    protected void dropInventory(World world, int x, int y, int z)
    {
        LogHelper.info("dropping Inventory");
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null && itemStack.stackSize > 0) {
                Random rand = new Random();
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());
                if (itemStack.hasTagCompound()) {
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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntityLightContainer tileEntity = (TileEntityLightContainer) world.getTileEntity(x, y, z);
        if (tileEntity != null && player.isSneaking() && player.getHeldItem() == null) {
            GuiHelper.openGui(tileEntity, player, world, x, y, z);
            return true;
        } else if (player.getHeldItem() == null) {

            GuiHelper.openGui(tileEntity, player, world, x, y, z);
            return true;

        } else if (player.getHeldItem().getItem() instanceof ItemBulbs) {// && player.isSneaking()) {

            if (!world.isRemote && tileEntity.getStackInSlot(0) != null) {
                world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, tileEntity.getStackInSlot(0)));
                tileEntity.decrStackSize(0, 1);
                //LogHelper.info("dropping");
            }
            ItemStack newStack = player.getHeldItem().copy();
            newStack.stackSize = 1;
            tileEntity.setInventorySlotContents(0, newStack);
            player.getHeldItem().stackSize = player.getHeldItem().stackSize - 1;
            tileEntity.updateLightLevel();
            return true;
        } else if (player.getHeldItem().getItem() instanceof ItemScrewDriver) {
            return false;
        } else {
            GuiHelper.openGui(tileEntity, player, world, x, y, z);
            return true;
        }
    }

    /*
    @Override
    public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        LogHelper.info("checkweakchanges");
        return true;
    }

    /**
     * If this block should be notified of weak changes.
     * Weak changes are changes 1 block away through a solid block.
     * Similar to comparators.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y position
     * @param z Z position
     * @param side The side to check
     * @return true To be notified of changes
     *
    @Override
    public boolean getWeakChanges(IBlockAccess world, int x, int y, int z)
    {
        LogHelper.info("getweakchanges");
        return true;
    }*/

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventData) {
        super.onBlockEventReceived(world, x, y, z, eventId, eventData);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(eventId, eventData);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }


    public void notifyBlockChange(World world, int x, int y, int z, TileEntityLightContainer entity) {
        if (world.isRemote) {
            //LogHelper.info("ClientMode");
        } else {
            entity.setPowerLevel(world.getStrongestIndirectPower(x, y, z));
            bulbs.network.sendToAllAround(new messageBulbs(x + ":" + y + ":" + z + ":" + connectNeighbours[0] + ":" + connectNeighbours[1] + ":" + connectNeighbours[2] + ":" + connectNeighbours[3] + ":" + connectNeighbours[4] + ":" + connectNeighbours[5]), new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, 300));
        }
    }

}

package de.schimski.bulbs.tileEntity;

import de.schimski.bulbs.bulbs;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;

public class TileEntityGridLight extends TileEntityBulbs implements IInventory{

    private boolean[] boolConnect = {false, false, false, false};
    private ItemStack[] inventory;
    private int lightLevel = -1;
    private float lightLevelFloat = 0;


    /**
     * Server sync counter (once per 20 ticks)
     */
    private int ticksSinceSync;

    public TileEntityGridLight() {
        this(-1);
    }

    public TileEntityGridLight (int metadata)
    {
        super();
        inventory = new ItemStack[1];
        if (metadata >= 0) {
            this.state = (byte) metadata;
        }
        this.lightLevelFloat = 0;
        this.lightLevel = 0;
    }

    public byte getState() {
        return state;
    }

    public boolean neighboursAreClose()
    {
        return this.neighbourCount() == 2 && ((boolConnect[0] && (boolConnect[3] || boolConnect[1])) || (boolConnect[2] && (boolConnect[3] || boolConnect[1])));
    }

    public void setNeighbour(int side, boolean connect) {
        boolConnect[side] = connect;
        writeToNBT(new NBTTagCompound());
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        //worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 5, 1);
    }

    public void updateEntity()
    {
        /*
        super.updateEntity();
        LogHelper.info("TickSync: " + ticksSinceSync);
        if (++ticksSinceSync % 80 * 4 == 0) {
            LogHelper.info("Syncing");
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 5, 2);
        }*/
    }

    public float getLightLevel() {
        return lightLevelFloat;
    }

    public void updateLightLevel(boolean forceUpdate) {
        LogHelper.info("Try updating");
        int lightLevelNew = (inventory[0] != null) ? inventory[0].getItemDamage() : -1;
        LogHelper.info(lightLevelNew);
        if (lightLevelNew != lightLevel || forceUpdate || true) {
            lightLevel = (lightLevelNew == -1) ? 0 : lightLevelNew;
            LogHelper.info("Do update: " + lightLevel);
            lightLevelFloat = ((lightLevel + 1) * (float) 1/16);
            addLight();
            //Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        }
    }

    private void addLight() {
        LogHelper.info("Recalculating Light with lightLevel " + lightLevel);
        this.worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord, lightLevel);
        //this.worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, xCoord, 3,3,3);
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        for (int x = xCoord-1; x < xCoord + 2; x++) {
            for (int y = yCoord-1; y < yCoord + 2; y++) {
                for (int z = zCoord-1; z < zCoord + 2; z++) {
                    TileEntity entity = worldObj.getTileEntity(x,y,z);
                    if (entity == null || !(entity instanceof TileEntityBulbs)) {
                        this.worldObj.updateLightByType(EnumSkyBlock.Block, x, y, z);
                    }
                }
            }
        }
    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        //LogHelper.info("Reading NBT");
        super.readFromNBT(nbt);
        for (int i = 0; i < boolConnect.length; i++)
        {
            boolConnect[i] = nbt.getBoolean("connect" + String.valueOf(i));
        }

        NBTTagList tagList = nbt.getTagList("Inventory",10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        //LogHelper.info("Writing NBT");
        if (nbt != null) {
            super.writeToNBT(nbt);
            for (int i = 0; i <boolConnect.length; i++)
            {
                nbt.setBoolean("connect" + String.valueOf(i), boolConnect[i]);
            }
        }

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        nbt.setTag("Inventory", itemList);

    }

    public int neighbourCount()
    {
        int count = 0;
        for (boolean i: boolConnect)
        {
            if (i)
            {
                count ++;
            }
        }
        return count;
    }

    public boolean hasGridLightNeighbour(int side)
    {
        return boolConnect[side];
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        LogHelper.info("Contents set");
        updateLightLevel(true);
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 5, 1);
    }

    @Override
    public String getInventoryName() {
        return "bulbs.gridLight";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }


    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {
        updateLightLevel(true);
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 5, 1);
    }

    @Override
    public void closeInventory() {
        //worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        LogHelper.info("Close inventory");
        updateLightLevel(true);
        LogHelper.info("internal lightlevel: " + this.getLightLevel());
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 5, 1);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
    }
}
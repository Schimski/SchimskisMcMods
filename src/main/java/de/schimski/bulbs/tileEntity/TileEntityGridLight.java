package de.schimski.bulbs.tileEntity;

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

public class TileEntityGridLight extends TileEntityBulbs implements IInventory{

    private boolean[] boolConnect = {false, false, false, false};
    private ItemStack[] inventory;
    private int lightLevel;

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
    }

    private void updateLightLevel() {
        lightLevel = (inventory[0] != null) ? inventory[0].getItemDamage() : -1;
        if (inventory[0] != null) {
            LogHelper.info(inventory[0].getItemDamage());
            LogHelper.info("LightLevel: " + (lightLevel));
            LogHelper.info("LightLevelFloat: " +  ((lightLevel + 1 )*(float)1/16));
        }

        this.worldObj.getBlock(xCoord, yCoord, zCoord).setLightLevel( (lightLevel + 1) * (float)1/16);
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
        LogHelper.info("Closing stack");
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
        updateLightLevel();
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

    }

    @Override
    public void closeInventory() {
        updateLightLevel();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        //worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 1, 2);
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
    }
}
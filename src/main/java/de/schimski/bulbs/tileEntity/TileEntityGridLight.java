package de.schimski.bulbs.tileEntity;

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

public class TileEntityGridLight extends TileEntity implements IInventory{
    private boolean[] boolConnect = {false, false, false, false};
    public NBTTagCompound nbtTag;
    private ItemStack[] inv;

    /*
    public TileEntityGridLight()
    {
        super();
    }*/

    public TileEntityGridLight (int metadata)
    {
        super();
        this.blockMetadata = metadata;
        this.nbtTag = new NBTTagCompound();
        inv = new ItemStack[9];
        readFromNBT(nbtTag);
    }


    public boolean neighboursAreClose()
    {
        return this.neighbourCount() == 2 && ((boolConnect[0] && (boolConnect[3] || boolConnect[1])) || (boolConnect[2] && (boolConnect[3] || boolConnect[1])));
    }

    public void setNeighbour(int side, boolean connect) {
        boolConnect[side] = connect;
        this.writeToNBT(this.nbtTag);
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        for (int i = 0; i < boolConnect.length; i++)
        {
            boolConnect[i] = nbt.getBoolean("connect" + String.valueOf(i));
        }

        NBTTagList tagList = nbt.getTagList("Inventory",10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inv.length) {
                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        if (nbt != null) {
            super.writeToNBT(nbt);
            for (int i = 0; i <boolConnect.length; i++)
            {
                nbt.setBoolean("connect" + String.valueOf(i), boolConnect[i]);
            }
        }

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            ItemStack stack = inv[i];
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
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
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
        inv[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
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
        return 64;
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

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}

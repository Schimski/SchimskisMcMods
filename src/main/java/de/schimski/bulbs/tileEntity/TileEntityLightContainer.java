package de.schimski.bulbs.tileEntity;

import de.schimski.bulbs.proxy.ClientProxy;
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

public class TileEntityLightContainer extends TileEntity  implements IInventory {

    /*
     * Side of the Block the light sticks to
     */

    private byte state;

    /*
     * for the moment unused lightcolor
     */

    //protected byte bulbColor;

    /*
     *  tickcounter, used for updating lights
     */

    private int ticksSinceSync;

    /*
     *  lightLevel, well, nothing more to say about that
     */

    private int lightLevel;

    /*
     *  the inventory of the light, able to hold 1 lightbulb!
     */

    private ItemStack[] inventory;

    /*
     *  boolConnect holds information about if a connected blockmodel should be loaded
     *  for a given side of the block
     */

    private boolean[] boolConnect;

    /*
    * probaply never needed as only subclasses are called with the standard constructor
    *

    public TileEntityLightContainer() {
        this(-1);
    }
    */

    public TileEntityLightContainer(int metadata) {
        super();
        this.lightLevel = 15;
        this.inventory = new ItemStack[1];
        this.boolConnect = new boolean[] {false, false, false, false};
        this.state =  metadata >= 0 ? (byte) metadata : 0;
    }


    /*
     *  Method for manually increasing the lightlevel
     */

    public void increaseLightLevel() {
        lightLevel = (lightLevel + 1) % 16;
        LogHelper.info(lightLevel);
        updateBlockMetadata();
    }


    /*
     *  Helper Method for finding errors
     *  should be removed when done
     */

    public String getBoolConnect() {
        String result = "";
        for(boolean i : boolConnect) {
            result += " " + i;
        }
        return result;
    }

    /*
     *  Methods to keep CLient and Server synced
     */

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }


    /*
     * True if light has two neighbors next to each other
     */

    public boolean neighboursAreClose() {
        return this.neighbourCount() == 2 && ((boolConnect[0] && (boolConnect[3] || boolConnect[1])) || (boolConnect[2] && (boolConnect[3] || boolConnect[1])));
    }


    /*
     * set boolConnect for given side
     */

    public void setNeighbour(int side, boolean connect) {
        boolConnect[side] = connect;
        writeToNBT(new NBTTagCompound());
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }


    /*
     * returns number of neighbors the light connects to
     */

    public int neighbourCount() {
        int count = 0;
        for (boolean i: boolConnect) {
            if (i) {
                count ++;
            }
        }
        return count;
    }


    /*
     * True if model connects to a given side
     * used in renderer
     */

    public boolean hasConnectingLightNeighbour(int side) {
        return boolConnect[side];
    }

    /*
     * NBT write and read methods
     */

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        for (int i = 0; i <boolConnect.length; i++) {
                nbt.setBoolean("connect" + String.valueOf(i), boolConnect[i]);
        }

        nbt.setByte("state", state);
        nbt.setInteger("lightLevel", lightLevel);

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

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        for (int i = 0; i < boolConnect.length; i++) {
            boolConnect[i] = nbt.getBoolean("connect" + String.valueOf(i));
        }

        state = nbt.getByte("state");
        lightLevel = nbt.getInteger("lightLevel");

        NBTTagList tagList = nbt.getTagList("Inventory",10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }


    /*
     *  The light should receive updates
     */

    public boolean canUpdate() {
        return true;
    }


    /*
     * update call
     * used for rerendering the block during the first 5 seconds, then again every 10 seconds
     */

    @Override
    public void updateEntity() {
        if (++ticksSinceSync < 100 || ticksSinceSync % 200 * 4 == 0) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    public void updateLightLevel() {

        lightLevel = getStackInSlot(0) != null ? lightLevel != 0 ? lightLevel : 15 : 0;
        updateBlockMetadata();
    }

    /*
     *  returns the state (side) of the light
     *  used for drawing the boundingbox
     */

    public byte getState() {
        return state;
    }


    /*
     *  updates the block with the current lightlevel and marks it for rendering
     */

    private void updateBlockMetadata() {
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, lightLevel, 3);
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }


    /*
     *  Implementation of all IInventory methods
     */

    @Override
    public int getSizeInventory() {
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
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }


/*
 * remain from bulb affected lightlevel
 *
        lightLevel = stack != null ? stack.getItemDamage() : 0;
        updateBlockMetadata();
*/
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
        ItemStack stack = getStackInSlot(0);

        lightLevel = stack != null ? 15 : 0;
        updateBlockMetadata();
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return false;
    }

}
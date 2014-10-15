package de.schimski.bulbs.inventory;

import de.schimski.bulbs.inventory.slot.SlotBulbs;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGridLight extends Container {

<<<<<<< HEAD
    private TileEntityGridLight tileEntityGridLight;

    public ContainerGridLight (InventoryPlayer inventoryPlayer, TileEntityGridLight tileEntityGridLight){
        this.tileEntityGridLight = tileEntityGridLight;

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlotToContainer(new Slot(tileEntityGridLight, j + i * 3, 90 + j * 18, 19 + i * 18));
            }
        }
=======
    protected TileEntityGridLight tileEntityGridLight;

    public ContainerGridLight (InventoryPlayer inventoryPlayer, TileEntityGridLight tileEntity){
        tileEntityGridLight = tileEntity;

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        //for (int i = 0; i < 3; i++) {
          //  for (int j = 0; j < 3; j++) {
                addSlotToContainer(new SlotBulbs(tileEntityGridLight, 0, 90, 18));
            //}
        //}
>>>>>>> 4d4aaa2402e00c07cac6964576dbed23e9c90cbe

        //commonly used vanilla code that adds the player's inventory
        bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntityGridLight.isUseableByPlayer(player);
    }


    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 36 + j * 18, 104 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 36 + i * 18, 162));
        }
    }

    /**
    * Shift clicking a slot
    */

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();
            if (slotIndex < 1)
            {
                if (!this.mergeItemStack(itemStack, 1, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStack, 0, 1, false))
            {
                return null;
            }
            if (itemStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return newItemStack;
    }

    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
    public void onContainerClosed(EntityPlayer entityPlayer)
    {
        super.onContainerClosed(entityPlayer);
        tileEntityGridLight.closeInventory();
    }
}
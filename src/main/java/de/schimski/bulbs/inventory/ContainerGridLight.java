package de.schimski.bulbs.inventory;

import de.schimski.bulbs.inventory.slot.SlotBulbs;
import de.schimski.bulbs.item.ItemBulbs;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGridLight extends ContainerBulbs {

    protected TileEntityGridLight tileEntityGridLight;

    public ContainerGridLight (InventoryPlayer inventoryPlayer, TileEntityGridLight tileEntity){
        tileEntityGridLight = tileEntity;

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        //for (int i = 0; i < 3; i++) {
        //  for (int j = 0; j < 3; j++) {
        addSlotToContainer(new SlotBulbs(tileEntityGridLight, 0, 107, 37));
        //}
        //}

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

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {

        ItemStack newItemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);
        SlotBulbs gridLightSlot = (SlotBulbs) inventorySlots.get(0);
        if (slot != null && slot.getHasStack()) {

            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();



            if (!gridLightSlot.getHasStack() && slotIndex > 0 && itemStack.getItem() instanceof ItemBulbs) {

                // Slot in gridLight is empty
                // clicked itemStack is a lightBulb
                // Click from inventory to gridLight

                newItemStack.stackSize = 1;
                itemStack.stackSize--;

                slot.putStack(itemStack);
                slot.onSlotChanged();

                gridLightSlot.putStack(newItemStack);
                gridLightSlot.onSlotChanged();

                // We do not want the rest of the stack to change position in inventory if 1 item has been sent to the gridLight

                if (itemStack.stackSize == 0)
                {

                    // If clicked stack has been completely send to another slot
                    // mark clocked slot EMPTY

                    slot.putStack(null);
                }
                else
                {

                    // If clicked stack has NOT been completely send to another slot
                    // mark clocked slot as CHANGED

                    slot.onSlotChanged();
                }

                return null;

            } else if (gridLightSlot.getHasStack() && slotIndex == 0) {

                // Slot in gridLight is NOT empty
                // Click from gridLight to inventory or ActionBar

                if (!this.mergeItemStack(itemStack, 1, inventorySlots.size(), false))
                {
                        return null;
                }
            } else if (/*gridLightSlot.getHasStack() && */ slotIndex > 0 && (slotIndex < inventorySlots.size()-9)) {

                // Slot in gridLight is NOT empty
                // Click from inventory to ActionBar

                if (!this.mergeItemStack(itemStack, inventorySlots.size()-9, inventorySlots.size(), false))
                {
                    return null;
                }
            } else if (/*gridLightSlot.getHasStack() && */ slotIndex >= (inventorySlots.size()-9)) {

                // Slot in gridLight is NOT empty
                // Click from ActionBar to inventory

                if (!this.mergeItemStack(itemStack, 1, inventorySlots.size() - 9, false)) {
                    return null;
                }
            }

            if (itemStack.stackSize == 0)
            {

                // If clicked stack has been completely send to another slot
                // mark clocked slot EMPTY

                slot.putStack(null);
            }
            else
            {

                // If clicked stack has NOT been completely send to another slot
                // mark clocked slot as CHANGED

                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }




        /*ItemStack newItemStack = null;
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
            else if (!this.mergeItemStack(itemStack, 1, inventorySlots.size(), false))
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
    }*/


}
package de.schimski.bulbs.inventory;

import de.schimski.bulbs.inventory.slot.SlotBulbs;
import de.schimski.bulbs.item.ItemBulbNormal;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerOfficeLight1 extends ContainerBulbs {

    protected TileEntityOfficeLight1 tileEntityOfficeLight1;

    public ContainerOfficeLight1(InventoryPlayer inventoryPlayer, TileEntityOfficeLight1 tileEntity){
        tileEntityOfficeLight1 = tileEntity;
        addSlotToContainer(new SlotBulbs(tileEntityOfficeLight1, 0, 107, 37));

        //commonly used vanilla code that adds the player's inventory
        bindPlayerInventory(inventoryPlayer);
    }



    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        tileEntityOfficeLight1.closeInventory();
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntityOfficeLight1.isUseableByPlayer(player);
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
        SlotBulbs officeLight1Slot = (SlotBulbs) inventorySlots.get(0);
        if (slot != null && slot.getHasStack()) {

            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();



            if (!officeLight1Slot.getHasStack() && slotIndex > 0 && itemStack.getItem() instanceof ItemBulbNormal) {

                // Slot in thinLight is empty
                // clicked itemStack is a lightBulb
                // Click from inventory to thinLight

                newItemStack.stackSize = 1;
                itemStack.stackSize--;

                slot.putStack(itemStack);
                slot.onSlotChanged();

                officeLight1Slot.putStack(newItemStack);
                officeLight1Slot.onSlotChanged();

                // We do not want the rest of the stack to change position in inventory if 1 item has been sent to the thinLight

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

            } else if (officeLight1Slot.getHasStack() && slotIndex == 0) {

                // Slot in thinLight is NOT empty
                // Click from thinLight to inventory or ActionBar

                if (!this.mergeItemStack(itemStack, 1, inventorySlots.size(), false))
                {
                        return null;
                }
            } else if (/*thinLightSlot.getHasStack() && */ slotIndex > 0 && (slotIndex < inventorySlots.size()-9)) {

                // Slot in thinLight is NOT empty
                // Click from inventory to ActionBar

                if (!this.mergeItemStack(itemStack, inventorySlots.size()-9, inventorySlots.size(), false))
                {
                    return null;
                }
            } else if (/*thinLightSlot.getHasStack() && */ slotIndex >= (inventorySlots.size()-9)) {

                // Slot in thinLight is NOT empty
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
}
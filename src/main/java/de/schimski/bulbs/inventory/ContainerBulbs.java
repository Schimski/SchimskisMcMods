package de.schimski.bulbs.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerBulbs extends Container
{
    protected final int PLAYER_INVENTORY_ROWS = 3;
    protected final int PLAYER_INVENTORY_COLUMNS = 9;
    
    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    //@Override
    protected boolean mergeItemStack(ItemStack itemStack, int fromSlot, int toSlot, boolean backwards)
    {
        boolean flag1 = false;
        int k = fromSlot;

        if (backwards)
        {
            k = toSlot - 1;
        }

        Slot slot;
        ItemStack newItemStack;


        if (itemStack.isStackable())
        {
            while (itemStack.stackSize > 0 && (!backwards && k < toSlot || backwards && k >= fromSlot))
            {
                slot = (Slot)this.inventorySlots.get(k);
                newItemStack = slot.getStack();

                if (newItemStack != null && newItemStack.getItem() == itemStack.getItem() && (!itemStack.getHasSubtypes() || itemStack.getItemDamage() == newItemStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(itemStack, newItemStack))
                {
                    int l = newItemStack.stackSize + itemStack.stackSize;

                    if (l <= itemStack.getMaxStackSize())
                    {
                        itemStack.stackSize = 0;
                        newItemStack.stackSize = l;
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                    else if (newItemStack.stackSize < itemStack.getMaxStackSize())
                    {
                        itemStack.stackSize -= itemStack.getMaxStackSize() - newItemStack.stackSize;
                        newItemStack.stackSize = itemStack.getMaxStackSize();
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                }

                if (backwards)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        if (itemStack.stackSize > 0)
        {
            if (backwards)
            {
                k = toSlot - 1;
            }
            else
            {
                k = fromSlot;
            }

            while (!backwards && k < toSlot || backwards && k >= fromSlot)
            {
                slot = (Slot)this.inventorySlots.get(k);
                newItemStack = slot.getStack();

                if (newItemStack == null)
                {
                    if (k==0) {
                        newItemStack = itemStack.copy();
                        newItemStack.stackSize = 1;
                        slot.putStack(newItemStack.copy());
                        slot.onSlotChanged();
                        itemStack.stackSize = itemStack.stackSize-1;
                        flag1 = true;
                        break;
                    } else {
                        slot.putStack(itemStack.copy());
                        slot.onSlotChanged();
                        itemStack.stackSize = 0;
                        flag1 = true;
                        break;
                    }
                }

                if (backwards)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        return flag1;
    }


}
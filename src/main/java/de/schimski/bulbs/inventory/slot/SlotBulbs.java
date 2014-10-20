package de.schimski.bulbs.inventory.slot;

import de.schimski.bulbs.item.ItemBulbNormal;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBulbs extends Slot{


    public SlotBulbs(IInventory inventory, int slotId, int x, int y) {
        super(inventory, slotId, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return (stack.getItem() instanceof ItemBulbNormal);
    }
}

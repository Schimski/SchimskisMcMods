package de.schimski.bulbs.creativetab;

import de.schimski.bulbs.init.ModItems;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBulbs {
    public static final CreativeTabs BULBS_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public Item getTabIconItem() {
            return ModItems.lightBulb;
        }
    };
}



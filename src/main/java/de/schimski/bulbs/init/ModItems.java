package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.item.ItemBulbs;
import de.schimski.bulbs.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static final ItemBulbs lightBulb = new ItemBulbs();


    public static void init()
    {
        GameRegistry.registerItem(lightBulb, "lightBulb");
    }
}

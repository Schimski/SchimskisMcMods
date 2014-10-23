package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.item.ItemBulbNormal;
import de.schimski.bulbs.item.ItemBulbRainbow;
import de.schimski.bulbs.item.ItemScrewDriver;
import de.schimski.bulbs.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static final ItemBulbNormal lightBulb = new ItemBulbNormal();
    public static final ItemBulbRainbow lightBulbRainbow = new ItemBulbRainbow();
    public static final ItemScrewDriver screwDriver = new ItemScrewDriver();


    public static void init() {
        GameRegistry.registerItem(lightBulb, "lightBulb");
        GameRegistry.registerItem(lightBulbRainbow, "lightBulbRainbow");
        GameRegistry.registerItem(screwDriver, "screwDriver");

    }
}

package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
    public static void init()
    {
        /*
         *  Item Recipes
         */

        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulbHolding,8), " i ", "i i", " i ", 'i', new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(ModItems.screwDriver,1), "  i", " i ", "s  ", 'i', new ItemStack(Items.iron_ingot), 's', new ItemStack(Items.stick));


        /*
         *  Bulb Recipes
         */


        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,0), " g ", "gcg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'c', new ItemStack(Items.coal), 'i', new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,0), " g ", "gdg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'd', new ItemStack(Items.dye,1,0), 'i', new ItemStack(Items.iron_ingot));

        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,1), " g ", "gag", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'a', new ItemStack(Items.apple), 'i', new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,1), " g ", "gdg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'd', new ItemStack(Items.dye,1,1), 'i', new ItemStack(Items.iron_ingot));

        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,2), " g ", "glg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'l', new ItemStack(Blocks.leaves), 'i', new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,2), " g ", "gdg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'd', new ItemStack(Items.dye,1,2), 'i', new ItemStack(Items.iron_ingot));

        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,3), " g ", "glg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'l', new ItemStack(Items.leather), 'i', new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(ModItems.lightBulb,4,3), " g ", "gdg", " i ", 'g', new ItemStack((Blocks.glass_pane)), 'd', new ItemStack(Items.dye,1,3), 'i', new ItemStack(Items.iron_ingot));

        /*
         *  Light Recipes
         */

        GameRegistry.addRecipe(new ItemStack(ModBlocks.gridLight, 4), "bbb", "bhb", "bbb", 'b', new ItemStack(Blocks.iron_bars), 'h', new ItemStack(ModItems.lightBulbHolding));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.thinLight, 4), "   ", " h ", "iii", 'i', new ItemStack(Items.iron_ingot), 'h', new ItemStack(ModItems.lightBulbHolding));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockLight, 4), "ggg", "ghg", "ggg", 'g', new ItemStack(Blocks.glass_pane), 'h', new ItemStack(ModItems.lightBulbHolding));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.poleLight, 4), " i ", " h ", " i ", 'i', new ItemStack(Items.iron_ingot), 'h', new ItemStack(ModItems.lightBulbHolding));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.officeLight1, 4), "sss", " h ", " i ", 'i', new ItemStack(Items.iron_ingot), 'h', new ItemStack(ModItems.lightBulbHolding), 's', new ItemStack(Blocks.stone));
        GameRegistry.addRecipe(new ItemStack(ModBlocks.officeLight2, 4), "ihi", " i ", " i ", 'i', new ItemStack(Items.iron_ingot), 'h', new ItemStack(ModItems.lightBulbHolding));
    }
}

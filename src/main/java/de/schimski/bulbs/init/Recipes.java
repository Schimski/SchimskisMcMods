package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.gridLight, 4), "   ", "   ", "ddd", 'd', new ItemStack(Items.stick));
    }
}

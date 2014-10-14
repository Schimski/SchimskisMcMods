package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.block.BlockBulbs;
import de.schimski.bulbs.block.BlockBulbsContainer;
import de.schimski.bulbs.block.BlockGridLight;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockBulbsContainer gridLight = new BlockGridLight();

    public static void init()
    {
        GameRegistry.registerBlock(gridLight, "gridLight");

    }
}

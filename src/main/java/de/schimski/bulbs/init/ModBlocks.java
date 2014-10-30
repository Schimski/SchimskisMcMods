package de.schimski.bulbs.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.schimski.bulbs.block.*;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockGridLight gridLight = new BlockGridLight();
    public static final BlockThinLight thinLight = new BlockThinLight();
    public static final BlockOfficeLight1 officeLight1 = new BlockOfficeLight1();
    public static final BlockOfficeLight2 officeLight2 = new BlockOfficeLight2();
    public static final BlockBlockLight blockLight = new BlockBlockLight();
    public static final BlockPoleLight poleLight = new BlockPoleLight();
    public static final BlockStealthLight stealthLight = new BlockStealthLight();

    public static void init()
    {
        GameRegistry.registerBlock(gridLight, "gridLight");
        GameRegistry.registerBlock(thinLight, "thinLight");
        GameRegistry.registerBlock(officeLight1, "officeLight1");
        GameRegistry.registerBlock(officeLight2, "officeLight2");
        GameRegistry.registerBlock(blockLight, "blockLight");
        GameRegistry.registerBlock(poleLight, "poleLight");
        GameRegistry.registerBlock(stealthLight, "stealthLight");
    }
}

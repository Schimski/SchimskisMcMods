package de.schimski.bulbs.utility;

import de.schimski.bulbs.bulbs;
import de.schimski.bulbs.tileEntity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class GuiHelper {

    public static void openGui(TileEntity tileEntity, EntityPlayer player, World world, int x, int y, int z) {
        if (tileEntity instanceof TileEntityGridLight) {
            player.openGui(bulbs.instance, 0, world, x, y, z);
        } else if (tileEntity instanceof TileEntityThinLight) {
            player.openGui(bulbs.instance, 1, world, x, y, z);
        } else if (tileEntity instanceof TileEntityOfficeLight1) {
            player.openGui(bulbs.instance, 2, world, x, y, z);
        } else if (tileEntity instanceof TileEntityBlockLight) {
            player.openGui(bulbs.instance, 3, world, x, y, z);
        } else if (tileEntity instanceof TileEntityPoleLight) {
            player.openGui(bulbs.instance, 4, world, x, y, z);
        }
    }
}

package de.schimski.bulbs.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import de.schimski.bulbs.client.gui.inventory.GuiGridLight;
import de.schimski.bulbs.inventory.ContainerGridLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {

        if (id == 0)
        {
            TileEntityGridLight tileEntitygridLight = (TileEntityGridLight) world.getTileEntity(x,y,z);
            return new ContainerGridLight(entityPlayer.inventory, tileEntitygridLight);
        }

        return null;
    }
    @Override
    public Object getClientGuiElement ( int id, EntityPlayer entityPlayer, World world, int x, int y, int z ) {
        if(id == 0) {
            TileEntityGridLight tileEntityGridLight = (TileEntityGridLight) world.getTileEntity(x, y, z);
            return new GuiGridLight(entityPlayer.inventory, tileEntityGridLight);
        }

        return null;
    }


}

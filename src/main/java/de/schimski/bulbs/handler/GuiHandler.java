package de.schimski.bulbs.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import de.schimski.bulbs.client.gui.inventory.GuiGridLight;
import de.schimski.bulbs.client.gui.inventory.GuiOfficeLight1;
import de.schimski.bulbs.client.gui.inventory.GuiThinLight;
import de.schimski.bulbs.inventory.ContainerGridLight;
import de.schimski.bulbs.inventory.ContainerOfficeLight1;
import de.schimski.bulbs.inventory.ContainerThinLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.tileEntity.TileEntityOfficeLight1;
import de.schimski.bulbs.tileEntity.TileEntityThinLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {

        if (id == 0) {
            TileEntityGridLight tileEntityGridLight = (TileEntityGridLight) world.getTileEntity(x,y,z);
            return new ContainerGridLight(entityPlayer.inventory, tileEntityGridLight);
        } else if (id == 1) {
            TileEntityThinLight tileEntityThinLight = (TileEntityThinLight) world.getTileEntity(x,y,z);
            return new ContainerThinLight(entityPlayer.inventory, tileEntityThinLight);
        } else if (id == 2) {
            TileEntityOfficeLight1 tileEntityOfficeLight1 = (TileEntityOfficeLight1) world.getTileEntity(x,y,z);
            return new ContainerOfficeLight1(entityPlayer.inventory, tileEntityOfficeLight1);
        }

        return null;
    }
    @Override
    public Object getClientGuiElement ( int id, EntityPlayer entityPlayer, World world, int x, int y, int z ) {
        if(id == 0) {
            TileEntityGridLight tileEntityGridLight = (TileEntityGridLight) world.getTileEntity(x, y, z);
            return new GuiGridLight(entityPlayer.inventory, tileEntityGridLight);
        } else if(id == 1) {
            TileEntityThinLight tileEntityThinLight = (TileEntityThinLight) world.getTileEntity(x, y, z);
            return new GuiThinLight(entityPlayer.inventory, tileEntityThinLight);
        } else if(id == 2) {
            TileEntityOfficeLight1 tileEntityOfficeLight1 = (TileEntityOfficeLight1) world.getTileEntity(x, y, z);
            return new GuiOfficeLight1(entityPlayer.inventory, tileEntityOfficeLight1);
        }

        return null;
    }


}

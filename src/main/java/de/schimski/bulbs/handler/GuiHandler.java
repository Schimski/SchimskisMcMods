package de.schimski.bulbs.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import de.schimski.bulbs.bulbs;
import de.schimski.bulbs.client.gui.inventory.*;
import de.schimski.bulbs.inventory.*;
import de.schimski.bulbs.tileEntity.*;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
        } else if (id == 3) {
            TileEntityBlockLight tileEntityBlockLight = (TileEntityBlockLight) world.getTileEntity(x,y,z);
            return new ContainerBlockLight(entityPlayer.inventory, tileEntityBlockLight);
        } else if (id == 4) {
            TileEntityPoleLight tileEntityPoleLight = (TileEntityPoleLight) world.getTileEntity(x,y,z);
            return new ContainerPoleLight(entityPlayer.inventory, tileEntityPoleLight);
        } else if (id == 5) {
            TileEntityOfficeLight2 tileEntityOfficeLight2 = (TileEntityOfficeLight2) world.getTileEntity(x,y,z);
            return new ContainerOfficeLight2(entityPlayer.inventory, tileEntityOfficeLight2);
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
        } else if(id == 3) {
            TileEntityBlockLight tileEntityBlockLight = (TileEntityBlockLight) world.getTileEntity(x, y, z);
            return new GuiBlockLight(entityPlayer.inventory, tileEntityBlockLight);
        } else if(id == 4) {
            TileEntityPoleLight tileEntityPoleLight = (TileEntityPoleLight) world.getTileEntity(x, y, z);
            return new GuiPoleLight(entityPlayer.inventory, tileEntityPoleLight);
        } else if(id == 5) {
            TileEntityOfficeLight2 tileEntityOfficeLight2 = (TileEntityOfficeLight2) world.getTileEntity(x, y, z);
            return new GuiOfficeLight2(entityPlayer.inventory, tileEntityOfficeLight2);
        }

        return null;
    }
}

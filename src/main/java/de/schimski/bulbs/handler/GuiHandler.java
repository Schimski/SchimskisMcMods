package de.schimski.bulbs.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import de.schimski.bulbs.client.gui.GuiBasic;
import de.schimski.bulbs.client.gui.inventory.GuiGridLight;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement ( int ID, EntityPlayer player, World world, int x, int y, int z ) {
        if(ID == 0)
            return new GuiGridLight(player.inventory, (TileEntityGridLight)world.getTileEntity(x,y,z));
        return null;
    }


}

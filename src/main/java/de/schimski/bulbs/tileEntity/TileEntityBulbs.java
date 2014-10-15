package de.schimski.bulbs.tileEntity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBulbs extends TileEntity {
    protected byte state;
    public TileEntityBulbs() {
        super();
        state = 0;
    }

}

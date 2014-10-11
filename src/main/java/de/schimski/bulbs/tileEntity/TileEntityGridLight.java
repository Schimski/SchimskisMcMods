package de.schimski.bulbs.tileEntity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityGridLight extends TileEntity {

    public TileEntityGridLight() {
        super();
    }

    public TileEntityGridLight (int metadata) {
        super();
        this.blockMetadata = metadata;
    }
}

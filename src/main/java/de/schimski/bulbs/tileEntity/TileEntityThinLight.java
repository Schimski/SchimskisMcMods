package de.schimski.bulbs.tileEntity;

public class TileEntityThinLight extends TileEntityLightContainer{

    public TileEntityThinLight() {
        this(-1);
    }

    public TileEntityThinLight(int metadata) {
        super(metadata);
    }
}
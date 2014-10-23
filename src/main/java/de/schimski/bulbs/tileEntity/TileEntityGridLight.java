package de.schimski.bulbs.tileEntity;

public class TileEntityGridLight extends TileEntityLightContainer{

    public TileEntityGridLight() {
        this(-1);
    }

    public TileEntityGridLight (int metadata) {
        super(metadata);
    }

    @Override
    public String getInventoryName() {
        return "bulbs.gridLight";
    }

}
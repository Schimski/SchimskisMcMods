package de.schimski.bulbs.tileEntity;


public class TileEntityBlockLight extends TileEntityLightContainer {

    public TileEntityBlockLight() {
        this(-1);
    }

    public TileEntityBlockLight(int metadata) {
        super(metadata);
    }

    @Override
    public String getInventoryName() {

        return "bulbs.blockLight";
    }
}
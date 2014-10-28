package de.schimski.bulbs.tileEntity;


import de.schimski.bulbs.proxy.ClientProxy;

public class TileEntityBlockLight extends TileEntityLightContainer {

    public TileEntityBlockLight() {
        this(-1);
    }

    public TileEntityBlockLight(int metadata) {

        super(metadata);
        this.canRotate = true;
    }

    @Override
    public void increaseRotation() {
        rotation = (rotation + 1) %10;
    }

    @Override
    public String getInventoryName() {

        return "bulbs.blockLight";
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }
}
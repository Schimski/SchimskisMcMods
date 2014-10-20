package de.schimski.bulbs.tileEntity;


import de.schimski.bulbs.proxy.ClientProxy;

public class TileEntityThinLight extends TileEntityLightContainer {

    public TileEntityThinLight() {
        this(-1);
    }

    public TileEntityThinLight(int metadata) {
        super(metadata);
    }

    /*
    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }
    */
}
package de.schimski.bulbs.tileEntity;

import de.schimski.bulbs.proxy.ClientProxy;

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


    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }
}
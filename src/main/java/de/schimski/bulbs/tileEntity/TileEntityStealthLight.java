package de.schimski.bulbs.tileEntity;


import de.schimski.bulbs.proxy.ClientProxy;

public class TileEntityStealthLight extends TileEntityLightContainer {

    public TileEntityStealthLight() {
        this(-1);
    }

    public TileEntityStealthLight(int metadata) {
        super(metadata);
        this.canRotate = false;
    }

    @Override
    public String getInventoryName() {
        return "bulbs.officeLight2";
    }
/*
    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }*/

}
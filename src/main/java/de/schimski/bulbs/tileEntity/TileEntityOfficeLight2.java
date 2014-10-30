package de.schimski.bulbs.tileEntity;


import de.schimski.bulbs.proxy.ClientProxy;

public class TileEntityOfficeLight2 extends TileEntityLightContainer {

    public TileEntityOfficeLight2() {
        this(-1);
    }

    public TileEntityOfficeLight2(int metadata) {
        super(metadata);
        this.canRotate = false;
    }

    @Override
    public String getInventoryName() {
        return "bulbs.officeLight2";
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }

}
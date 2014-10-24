package de.schimski.bulbs.tileEntity;


public class TileEntityOfficeLight1 extends TileEntityLightContainer {

    public TileEntityOfficeLight1() {
        this(-1);
    }

    public TileEntityOfficeLight1(int metadata) {
        super(metadata);
        this.canRotate = true;
    }

    @Override
    public String getInventoryName() {
        return "bulbs.officeLight1";
    }


    @Override
    public void increaseRotation() {

        rotation = (rotation + 90) % 180;
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
package de.schimski.bulbs.tileEntity;


import de.schimski.bulbs.proxy.ClientProxy;
import de.schimski.bulbs.utility.LogHelper;

public class TileEntityPoleLight extends TileEntityLightContainer {

    public TileEntityPoleLight() {
        this(-1);
    }

    public TileEntityPoleLight(int metadata) {
        super(metadata);
        this.canRotate = true;
    }

    @Override
    public String getInventoryName() {
        return "bulbs.poleLight";
    }


    @Override
    public void increaseRotation() {

        state = (byte)((state + 2) % 6);
        for (int i = xCoord-1; i <= xCoord+1; i++) {
            for (int j = yCoord-1; j <= yCoord+1 ; j++) {
                for (int k = zCoord-1; k <= zCoord+1 ; k++) {
                    LogHelper.info(i + " - " + j + " - " + k);
                    worldObj.notifyBlockOfNeighborChange(i,j,k, worldObj.getBlock(xCoord, yCoord, zCoord));
                }
            }
        }
        //worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, lightLevel, 3);
        //worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        return true;
    }

}
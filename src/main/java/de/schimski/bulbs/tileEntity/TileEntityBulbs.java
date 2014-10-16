package de.schimski.bulbs.tileEntity;

import de.schimski.bulbs.block.BlockBulbsContainer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBulbs extends TileEntity {
    protected byte state;
    protected byte bulbColor;

    /**
     * Server sync counter (once per 20 ticks)
     */
    private int ticksSinceSync;

    public TileEntityBulbs() {
        super();
        state = 0;
        bulbColor = -1;
    }

    public void setLightLevel(float lightLevel)
    {
        ((BlockBulbsContainer) this.getWorldObj().getBlock(xCoord, yCoord, zCoord)).setLightLevel(lightLevel);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (++ticksSinceSync % 20 * 4 == 0) {
            //LogHelper.info("TickSync: " + ticksSinceSync);
            //worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.gridLight, 1, 2);
        }
    }

        @Override
    public boolean receiveClientEvent(int eventID, int otherInt)
    {
        if (eventID == 1)
        {

            return true;
        }
        else
        {
            return super.receiveClientEvent(eventID, otherInt);
        }
    }

}
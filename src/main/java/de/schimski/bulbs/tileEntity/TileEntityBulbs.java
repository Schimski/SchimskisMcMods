package de.schimski.bulbs.tileEntity;

import de.schimski.bulbs.block.BlockBulbsContainer;
import de.schimski.bulbs.init.ModBlocks;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBulbs extends TileEntity {
    protected byte state;
    protected byte bulbColor;


    public TileEntityBulbs() {
        super();
        state = 0;
        bulbColor = -1;
    }

    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity() {

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
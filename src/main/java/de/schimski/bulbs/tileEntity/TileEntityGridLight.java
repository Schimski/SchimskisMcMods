package de.schimski.bulbs.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGridLight extends TileEntity {
    public boolean rightConnect = false;
    public boolean downConnect = false;
    public boolean leftConnect = false;
    public boolean upConnect = false;

    public NBTTagCompound nbtTag;

    public void setNeighbour(char side, boolean connect) {
        switch (side) {
            case 'r':
                rightConnect = connect;
                break;
            default:
                rightConnect = false;
                break;
        }

        this.writeToNBT(this.nbtTag);
    }

    public TileEntityGridLight() {
        super();
    }

    public TileEntityGridLight (int metadata) {
        super();
        this.blockMetadata = metadata;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        if (nbt!=null){
            super.readFromNBT(nbt);
            this.rightConnect = nbt.getBoolean("right");
        } else {
            initializeNeighbours();
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("right", rightConnect);
        nbt.setBoolean("down", downConnect);
        nbt.setBoolean("left", leftConnect);
        nbt.setBoolean("up", upConnect);

        super.writeToNBT(nbt);
    }

    public void initializeNeighbours()
    {
        this.nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
    }


}

package de.schimski.bulbs.tileEntity;

import cpw.mods.fml.common.FMLCommonHandler;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGridLight extends TileEntity {
    private boolean[] boolConnect = {false, false, false, false};

    public NBTTagCompound nbtTag;

    public void updateEntity()
    {
        //LogHelper.info("update");

    }

    public void setNeighbour(int side, boolean connect) {
        boolConnect[side] = connect;
        this.writeToNBT(this.nbtTag);
//        this.updateEntity();
        LogHelper.info("setNeighbor: " + FMLCommonHandler.instance().getEffectiveSide());
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        LogHelper.info(neighbourCount());
    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    public TileEntityGridLight() {
        super();
    }

    public TileEntityGridLight (int metadata) {
        super();
        this.blockMetadata = metadata;
        this.nbtTag = new NBTTagCompound();
        readFromNBT(nbtTag);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        for (int i = 0; i < boolConnect.length; i++)
        {
            boolConnect[i] = nbt.getBoolean("connect" + String.valueOf(i));
            //LogHelper.info(boolConnect[i]);
        }
    }

    private void logNBT(NBTTagCompound nbt)
    {
        for (int i = 0; i <boolConnect.length; i++)
        {
            LogHelper.info(nbt.getBoolean("connect" + String.valueOf(i)));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        if (nbt != null) {
            super.writeToNBT(nbt);
            for (int i = 0; i <boolConnect.length; i++)
            {
                nbt.setBoolean("connect" + String.valueOf(i), boolConnect[i]);
                //LogHelper.info(boolConnect[i]);
            }
        }
    }

    public int neighbourCount()
    {
        //this.readFromNBT(this.nbtTag);
        int count = 0;
        for (int i = 0; i<boolConnect.length; i++)
        {
            if (boolConnect[i])
            {
                count ++;
            }
        }
        return count;
    }

    public boolean hasGridLightNeighbour(int side)
    {
        return boolConnect[side];
    }

    @Override
    public Packet getDescriptionPacket()
    {
        LogHelper.info("DescPacket");
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        //logNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        LogHelper.info("DataPacket");
        readFromNBT(pkt.func_148857_g());
        //LogHelper.info(FMLCommonHandler.instance().getEffectiveSide());
        //logNBT(pkt.func_148857_g());
        Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
}

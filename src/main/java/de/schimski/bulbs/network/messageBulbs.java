package de.schimski.bulbs.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import de.schimski.bulbs.utility.LogHelper;
import io.netty.buffer.ByteBuf;

public class messageBulbs implements IMessage{

    private String text;

    public int x;
    public int y;
    public int z;

    public boolean connectNeighbours[] = {false, false, false, false};

    public messageBulbs() {}

    public messageBulbs(String text)
    {
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf);
        //LogHelper.info(text);
        String[] splitText = text.split(":");

        x = Integer.valueOf(splitText[0]);
        y = Integer.valueOf(splitText[1]);
        z = Integer.valueOf(splitText[2]);

        for (int i = 3; i<7; i++) {

            connectNeighbours[i-3] = splitText[i].equals("false") ? false : true;
        }

    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

}

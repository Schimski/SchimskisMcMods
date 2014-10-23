package de.schimski.bulbs.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.schimski.bulbs.tileEntity.TileEntityGridLight;
import de.schimski.bulbs.tileEntity.TileEntityLightContainer;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.client.Minecraft;

public class messageBulbsHandler implements IMessageHandler<messageBulbs, IMessage> {
    @Override
    public IMessage onMessage(messageBulbs message, MessageContext ctx) {
        //LogHelper.info("MessageReceived");

        TileEntityLightContainer lightContainer = (TileEntityLightContainer)Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y,message.z);
        //LogHelper.info("X: " + message.x + "Y: " + message.y + "Z: " + message.z);

        //LogHelper.info(connectNeighbours[i]);
        if (lightContainer != null) {
            for (int i = 0; i < message.connectNeighbours.length; i++)
                lightContainer.setNeighbour(i, message.connectNeighbours[i]);
        }
        return null;
    }
}

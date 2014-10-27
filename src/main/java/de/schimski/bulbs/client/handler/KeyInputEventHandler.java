package de.schimski.bulbs.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import de.schimski.bulbs.client.settings.Keybindings;
import de.schimski.bulbs.utility.LogHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class KeyInputEventHandler {

    private static int mode = 0;
    private static int direction = 1;

    public static int mX = 9;
    public static int mY = 4;
    public static int mZ = 3;

    public static int rotX = -153;
    public static int rotY = 49;
    public static int rotZ = -36;

    private static void getPressedKeybinding() {
        if(Keybindings.keyX.isPressed()) {
            if (mode == 0) {
                mX = mX + direction;
            } else {
                rotX = rotX + direction;
            }
            //return Key.X;
        } else if(Keybindings.keyY.isPressed()) {
            if (mode == 0) {
                mY = mY + direction;
            } else {
                rotY = rotY + direction;
            }
            //return Key.Y;
        } else if(Keybindings.keyZ.isPressed()) {
            if (mode == 0) {
                mZ = mZ + direction;
            } else {
                rotZ = rotZ + direction;
            }
            //return Key.Z;
        } else if(Keybindings.keyMODE.isPressed()) {
            mode = (mode + 1) % 2;
            //return Key.SWITCHMODE;
            LogHelper.info(mode == 0 ? "Mode: Position" : "Mode: Rotation");
        } else if(Keybindings.keyDIRECTION.isPressed()) {
            direction = direction == 1 ? -1 : 1;
            LogHelper.info(direction == 1 ? "Mode: positive" : "Mode: negative");
            //return Key.SWITCHDIRECTION;
        } else {
            //return null;
        }
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
        //LogHelper.info("Key pressed");
        //getPressedKeybinding();
        //LogHelper.info("Position: " + mX + " - " + mY + " - " + mZ);
        //LogHelper.info("Rotation: " + rotX + " - " + rotY + " - " + rotZ);
    }


}

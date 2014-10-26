package de.schimski.bulbs.client.settings;

import de.schimski.bulbs.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybindings {
    public static KeyBinding keyX = new KeyBinding(Names.Keys.X, Keyboard.KEY_U, Names.Keys.CATEGORY);
    public static KeyBinding keyY = new KeyBinding(Names.Keys.Y, Keyboard.KEY_I, Names.Keys.CATEGORY);
    public static KeyBinding keyZ = new KeyBinding(Names.Keys.Z, Keyboard.KEY_O, Names.Keys.CATEGORY);
    public static KeyBinding keyMODE = new KeyBinding(Names.Keys.SWITCHMODE, Keyboard.KEY_N, Names.Keys.CATEGORY);
    public static KeyBinding keyDIRECTION = new KeyBinding(Names.Keys.SWITCHDIRECTION, Keyboard.KEY_M, Names.Keys.CATEGORY);
}


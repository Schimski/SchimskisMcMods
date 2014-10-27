package de.schimski.bulbs.client.gui;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.schimski.bulbs.utility.LogHelper;
import javafx.stage.Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TextOverlay extends Gui {

    private Minecraft mc;
    private static String text;
    private static int tickCounter;

    public static void renderText(String s) {
        tickCounter = 0;
        text = s;
    }

    public TextOverlay(Minecraft mc) {
        super();
        this.mc = mc;
        this.tickCounter = 0;
        this.text = "";
    }



    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void  onRenderExperienceBar(RenderGameOverlayEvent.Post event) {
        tickCounter++;
        if (tickCounter < 1000) {
            this.mc.fontRenderer.drawString(text, 50 , 30, 0xffFFFFFF); //49854
        }
    }
}

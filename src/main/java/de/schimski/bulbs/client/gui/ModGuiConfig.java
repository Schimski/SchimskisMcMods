package de.schimski.bulbs.client.gui;

import com.mojang.realmsclient.gui.ConfigureWorldScreen;
import cpw.mods.fml.client.config.GuiConfig;
import de.schimski.bulbs.handler.ConfigurationHandler;
import de.schimski.bulbs.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen guiScreen)
    {

        super(guiScreen,
            new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
            Reference.MOD_ID,
            false,
            false,
            GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}

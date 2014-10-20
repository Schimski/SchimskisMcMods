package de.schimski.bulbs.proxy;


import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    public abstract void initRenderingAndTextures();
    public abstract void registerTileEntities();
    public abstract void setCustomRenderers();
}

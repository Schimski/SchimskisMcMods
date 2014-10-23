package de.schimski.bulbs.utility;

import cpw.mods.fml.common.FMLLog;
import de.schimski.bulbs.reference.Reference;
import org.apache.logging.log4j.Level;


public class LogHelper {

    public static void log(Level logLevel, Object object) {
        FMLLog.log(Reference.MOD_NAME, logLevel,String.valueOf(object));
    }

    public static void all(Object object) {
        log(Level.ALL, object);
    }
    public static void info(Object object) {
        log(Level.INFO, object);
    }
    public static void debug(Object object) {
        log(Level.DEBUG, object);
    }
    public static void error(Object object) {
        log(Level.ERROR, object);
    }
}

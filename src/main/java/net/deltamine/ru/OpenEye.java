package net.deltamine.ru;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = OpenEye.ID, name = OpenEye.NAME, version = OpenEye.VERSION)
public class OpenEye {

    public static final String ID = "openeye";
    public static final String NAME = "OpenEye";
    public static final String VERSION = "0.3.1";

    @Instance
    public static OpenEye instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {

    }
}

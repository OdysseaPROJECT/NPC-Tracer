package net.deltamine.ru;

import net.deltamine.ru.pack.Pack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.management.ManagementFactory;
import java.util.UUID;

@Mod(modid = Lore.ID, name = Lore.NAME, version = Lore.VERSION)
public class Core {

    @Mod.Instance
    public static Core instance;

    org.apache.logging.log4j.Logger LOGGER;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();

        Minecraft minecraft = Minecraft.getMinecraft();

        String defaultPack = minecraft.mcDefaultResourcePack.getPackName();

        if(defaultPack != "MinecraftFont") {
            LOGGER.warn("Default resource pack was compromised! Please, ensure you using stable edition of pack!");
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        UUID uuid = UUID.randomUUID();

        LOGGER.info("Current session UUID (non-player): " + uuid);
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        String processName = null;

        if(Loader.isModLoaded("It's the little things") == true) {
            String processFullname = ManagementFactory.getRuntimeMXBean().getName();

            processName = processFullname.split("@")[1];
        }

        if(processName != null) {

            switch(processName) {
                case "Magicae": LOGGER.info("Force-code Version: " + Pack.MAGICAE);
                case "Fabrica": LOGGER.info("Force-code Version: " + Pack.FABRICA);
                case "Statera": LOGGER.info("Force-code Version: " + Pack.STATERA);
                case "Insula": LOGGER.info("Force-code Version: " + Pack.INSULA);
                case "Odyssea": LOGGER.info("Force-code Version: " + Pack.ODYSSEA);
            }
        }
    }
}

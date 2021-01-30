package net.deltamine.ru;

import gnu.trove.map.TMap;
import net.deltamine.ru.network.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.List;

@Mod(modid = OpenEye.ID, name = OpenEye.NAME, version = OpenEye.VERSION)
public class OpenEye {

    public static final String ID = "openeye";
    public static final String NAME = "OpenEye";
    public static final String VERSION = "0.3.2-alpha";

    public static FMLEventChannel network;

    public static TMap<EntityPlayer, List<EntityLiving>> entityLost;

    org.apache.logging.log4j.Logger LOGGER;

    @Instance
    public static OpenEye instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newEventDrivenChannel("openeye");
        network.register(new PacketHandler());

        PacketHandler.addPacket(new CheckEntitySC());
        PacketHandler.addPacket(new CheckEntityAnswSC());
        PacketHandler.addPacket(new ParticleSC());

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void cleanEnv(PlayerEvent.PlayerLoggedOutEvent event) {}

    @SubscribeEvent
    public void worldTickEvent(TickEvent.WorldTickEvent event) {}

    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {}
}

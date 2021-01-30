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
    public static final String VERSION = "0.3.1";

    public static FMLEventChannel network;

    public static TMap<EntityPlayer, List<EntityLiving>> entityLost;

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
        network = NetworkRegistry.INSTANCE.newEventDrivenChannel("openeye");
        network.register(new PacketHandler());

        PacketHandler.addPacket((PacketConstructor) new CheckEntitySC());
        PacketHandler.addPacket((PacketConstructor) new CheckEntityAnswSC());
        PacketHandler.addPacket((PacketConstructor) new ParticleSC());

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void cleaner(PlayerEvent.PlayerLoggedOutEvent ev) {}

    @SubscribeEvent
    public void ticker(TickEvent.WorldTickEvent e) {}

    @SubscribeEvent
    public void ticker(TickEvent.PlayerTickEvent ev) {}
}

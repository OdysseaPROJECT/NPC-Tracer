package deltamine.ru;

import deltamine.ru.handlers.RegistryHandler;
import deltamine.ru.network.CheckEntityAnswSC;
import deltamine.ru.network.CheckEntitySC;
import deltamine.ru.network.PacketHandler;
import deltamine.ru.network.ParticleSC;
import deltamine.ru.proxy.CommonProxy;
import deltamine.ru.util.ConfigHandler;
import gnu.trove.map.TMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

@Mod(modid = Lore.ID, name = Lore.NAME, version = Lore.VERSION)
public class Jupiter {
    @Instance
    public static Jupiter instance;

    public static File config;

    @SidedProxy(clientSide = Lore.CLIENT, serverSide = Lore.SERVER)
    public static CommonProxy proxy;

    public static FMLEventChannel network;

    public static TMap<EntityPlayer, List<EntityLiving>> entityLost;

    static org.apache.logging.log4j.Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public void init(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newEventDrivenChannel("jupiter");
        network.register(new PacketHandler());

        if(ConfigHandler.traceEntitySC == true) {
            PacketHandler.addPacket(new CheckEntitySC());
            logger.info("Tracing loaded entities!");
        }
        if(ConfigHandler.traceEntityAnswSC == true) {
            PacketHandler.addPacket(new CheckEntityAnswSC());
            logger.info("Tracing entities without signal with server (no connection)!");
        }
        if(ConfigHandler.traceParticleSC == true) {
            PacketHandler.addPacket(new ParticleSC());
            logger.info("Tracing particles!");
        }

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void cleanEnv(PlayerEvent.PlayerLoggedOutEvent event) {}

    @SubscribeEvent
    public void worldTickEvent(TickEvent.WorldTickEvent event) {}

    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {}

    public static Logger getLogger() {
        return logger;
    }
}

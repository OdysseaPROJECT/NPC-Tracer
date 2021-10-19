package tracingnpc;

import gnu.trove.map.TMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tracingnpc.handlers.RegistryHandler;
import tracingnpc.network.CheckEntityAnswSC;
import tracingnpc.network.CheckEntitySC;
import tracingnpc.network.PacketHandler;
import tracingnpc.network.ParticleSC;
import tracingnpc.proxy.CommonProxy;
import java.io.File;
import java.util.List;

@Mod(modid = "tracingnpc", name = "NPC-Tracer", version = "1.0-SNAPSHOT")
public class NPCTracer {
    @Instance
    public static NPCTracer instance;

    public static File config;

    @SidedProxy(clientSide = "tracingnpc.proxy.ClientProxy", serverSide = "tracingnpc.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static FMLEventChannel network;

    public static TMap<EntityPlayer, List<EntityLiving>> entityLost;

    public static Logger LOGGER = LogManager.getLogger("NPC-Tracer");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {  }

    @EventHandler
    public void init(FMLInitializationEvent event) { }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newEventDrivenChannel("npc_tracer");
        network.register(new PacketHandler());

        PacketHandler.addPacket(new CheckEntitySC());
        LOGGER.info("TRACING: Load entities!");

        PacketHandler.addPacket(new CheckEntityAnswSC());
        LOGGER.info("TRACING: Load entities without connection to the server!");

        PacketHandler.addPacket(new ParticleSC());
        LOGGER.info("TRACING: Load particles!");

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void cleanEnv(PlayerEvent.PlayerLoggedOutEvent event) {}

    @SubscribeEvent
    public void worldTickEvent(TickEvent.WorldTickEvent event) {}

    @SubscribeEvent
    public void playerTickEvent(TickEvent.PlayerTickEvent event) {}
}

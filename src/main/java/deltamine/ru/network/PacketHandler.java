package deltamine.ru.network;

import com.google.common.collect.Lists;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.ArrayList;

public class PacketHandler extends Thread {

    public static final Object GLOBAL_LOCK = new Object();

    private static ArrayList<PacketConstructor> packets = Lists.newArrayList();

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) throws IOException {
        if(!event.getPacket().channel().equals("existence_npc_tracer"))
            return;

        ByteBufInputStream inputStream = new ByteBufInputStream(event.getPacket().payload());

        String name = inputStream.readUTF();

        for(PacketConstructor packet : packets) {
            if(packet.packetUniqueName().equals(name)) {
                if(!packet.getProcessSide().equals(Side.CLIENT)) return;

                EntityPlayer entityPlayerMP = Minecraft.getMinecraft().player;
                World world = entityPlayerMP.world;

                int coordX = (int) entityPlayerMP.posX;
                int coordY = (int) entityPlayerMP.posY;
                int coordZ = (int) entityPlayerMP.posZ;

                packet.readPacket(inputStream);
                packet.processPacket(coordX, coordY, coordZ, entityPlayerMP, world);
            }
        }
    }

    @SubscribeEvent
    public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event) throws IOException {
        if(!event.getPacket().channel().equals("existence_npc_tracer"))
            return;
    }

    public static void addPacket(PacketConstructor packet) {
        if(packet != null && !packets.contains(packet))
            packets.add(packet);
    }
}
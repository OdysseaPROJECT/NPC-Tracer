package net.deltamine.ru.network;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.deltamine.ru.OpenEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PacketConstructor extends Thread {

    private DataInput input;

    private DataOutput output;

    private boolean rec;

    public PacketConstructor() {
        setDaemon(true);
    }

    public PacketConstructor(DataInput input, DataOutput output) {
        this.input = input;
        this.output = output;
    }

    protected void writePacket(DataOutput output) throws IOException {}

    protected void readPacket(DataInput input) throws IOException {}

    public void processPacket(double posX, double posY, double posZ, EntityPlayer player, World world) {}

    public Side getProcessSide() {
        return Side.CLIENT;
    }

    public String packetUniqueName() {
        return "";
    }

    @SideOnly(Side.CLIENT)
    public void sendToServer() {
        OpenEye.network.sendToServer(createPacket());
    }

    public void sendToPlayer(EntityPlayer player) {}

    private FMLProxyPacket createPacket() {
        PacketBuffer packetBuf = (PacketBuffer) Unpooled.buffer();

        ByteBufOutputStream out = new ByteBufOutputStream(packetBuf);
        try {
            out.writeUTF(packetUniqueName());
            writePacket(out);

            out.flush();
        } catch (IOException iOException) {
            try {
                out.close();
            } catch (IOException iOException1) {}

        } finally {
            try {
                out.close();
            } catch (IOException iOException) {}
        }

        return new FMLProxyPacket(packetBuf, "existence_npc_tracer");
    }

    public void run() {}
}


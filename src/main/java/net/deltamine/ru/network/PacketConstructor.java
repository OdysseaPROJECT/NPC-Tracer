package net.deltamine.ru.network;

import io.netty.buffer.ByteBuf;
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

    public void processPacket(double posx, double posy, double posz, EntityPlayer p, World w) {}

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
        PacketBuffer e = (PacketBuffer) Unpooled.buffer();
        ByteBufOutputStream out = new ByteBufOutputStream(e);
        try {
            out.writeUTF(packetUniqueName());
            writePacket((DataOutput)out);
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
        return new FMLProxyPacket(e, "existence_npc_tracer");
    }

    public void run() {}
}


package net.deltamine.ru.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Random;

public class ParticleSC extends PacketConstructor {
    private double x;

    private double y;

    private double z;

    private double w;

    private double h;

    private static Random rand = new Random();

    public ParticleSC() {}

    public ParticleSC(double x, double y, double z, double w, double h) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
    }

    protected void readPacket(DataInput input) throws IOException {
        this.x = input.readDouble();
        this.y = input.readDouble();
        this.z = input.readDouble();
        this.w = input.readDouble();
        this.h = input.readDouble();
    }

    protected void writePacket(DataOutput output) throws IOException {
        output.writeDouble(this.x);
        output.writeDouble(this.y);
        output.writeDouble(this.z);
        output.writeDouble(this.w);
        output.writeDouble(this.h);
    }

    public void processPacket(double posx, double posy, double posz, EntityPlayer p, World world) {
        for (int i = 0; i < 25; i++)
            world.spawnParticle(EnumParticleTypes.PORTAL, this.x + (rand.nextDouble() - 0.5D) * this.w, this.y + rand
                    .nextDouble() * this.h - 0.25D, this.z + (rand
                    .nextDouble() - 0.5D) * this.w, (rand
                    .nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand
                    .nextDouble() - 0.5D) * 2.0D);
    }

    public Side getProcessSide() {
        return Side.CLIENT;
    }

    public String packetUniqueName() {
        return "openeye_particle_sc";
    }
}

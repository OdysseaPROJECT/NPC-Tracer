package tracingnpc.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Random;

public class ParticleSC extends PacketConstructor {

    private double coordX;
    private double coordY;
    private double coordZ;

    private double width;
    private double height;

    private static Random random = new Random();

    public ParticleSC() {}

    public ParticleSC(double x, double y, double z, double w, double h) {
        this.coordX = x;
        this.coordY = y;
        this.coordZ = z;

        this.width = w;
        this.height = h;
    }

    protected void readPacket(DataInput input) throws IOException {
        this.coordX = input.readDouble();
        this.coordY = input.readDouble();
        this.coordZ = input.readDouble();

        this.width = input.readDouble();
        this.height = input.readDouble();
    }

    protected void writePacket(DataOutput output) throws IOException {
        output.writeDouble(this.coordX);
        output.writeDouble(this.coordY);
        output.writeDouble(this.coordZ);

        output.writeDouble(this.width);
        output.writeDouble(this.height);
    }

    public void processPacket(double posX, double posY, double posZ, EntityPlayer player, World world) {
        for (int i = 0; i < 25; i++)
            world.spawnParticle(EnumParticleTypes.PORTAL, this.coordX + (random.nextDouble() - 0.5D) * this.width, this.coordY + random
                    .nextDouble() * this.height - 0.25D, this.coordZ + (random
                    .nextDouble() - 0.5D) * this.width, (random
                    .nextDouble() - 0.5D) * 2.0D, -random.nextDouble(), (random
                    .nextDouble() - 0.5D) * 2.0D);
    }

    public Side getProcessSide() {
        return Side.CLIENT;
    }

    public String packetUniqueName() {
        return "tracing_particles_sc";
    }
}

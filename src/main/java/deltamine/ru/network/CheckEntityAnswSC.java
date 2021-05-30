package deltamine.ru.network;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CheckEntityAnswSC extends PacketConstructor {

    int[] lostEntities;

    public CheckEntityAnswSC() {}

    public CheckEntityAnswSC(int[] lostEntities) {
        this.lostEntities = lostEntities;
    }

    protected void readPacket(DataInput input) throws IOException {
        int size = input.readInt();

        this.lostEntities = new int[size];

        for (int i = 0; i < size; i++)
            this.lostEntities[i] = input.readInt();
    }

    protected void writePacket(DataOutput output) throws IOException {
        output.writeInt(this.lostEntities.length);

        for (int i : this.lostEntities)
            output.writeInt(i);
    }

    public void processPacket(double posx, double posy, double posz, EntityPlayer p, World w) {}

    private void addToTracing(EntityPlayer player, EntityLiving entity) {}

    public Side getProcessSide() {
        return Side.SERVER;
    }

    public String packetUniqueName() {
        return "tracer_check_entities_answ_cs";
    }
}

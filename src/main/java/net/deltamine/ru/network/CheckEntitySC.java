package net.deltamine.ru.network;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CheckEntitySC extends PacketConstructor {
    public Map<Integer, String> entities;

    public CheckEntitySC() {}

    public CheckEntitySC(List<EntityLiving> entities) {
        this.entities = Maps.newHashMapWithExpectedSize(entities.size());
        for (EntityLiving entity : entities)
            this.entities.put(Integer.valueOf(entity.getCustomNameTag()), entity.getClass().getSimpleName());
    }

    protected void readPacket(DataInput input) throws IOException {
        int size = input.readInt();
        this.entities = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++)
            this.entities.put(Integer.valueOf(input.readInt()), input.readUTF());
    }

    protected void writePacket(DataOutput output) throws IOException {
        output.writeInt(this.entities.size());
        for (Iterator<Integer> iterator = this.entities.keySet().iterator(); iterator.hasNext(); ) {
            int key = ((Integer)iterator.next()).intValue();
            output.writeInt(key);
            output.writeUTF(this.entities.get(Integer.valueOf(key)));
        }
    }

    public void processPacket(double posx, double posy, double posz, EntityPlayer p, World w) {
        List<EntityLiving> clientEntities = Lists.newArrayList();
        for (Entity entity : w.loadedEntityList) {
            if (!(entity instanceof EntityPlayer) && entity instanceof EntityLiving)
                clientEntities.add((EntityLiving)entity);
        }
        for (EntityLivingBase entity : clientEntities) {
            if (this.entities.containsKey(Integer.valueOf(entity.getCustomNameTag())) && ((String)this.entities
                    .get(Integer.valueOf(entity.getCustomNameTag()))).equals(entity.getClass().getSimpleName()))
                this.entities.remove(Integer.valueOf(entity.getCustomNameTag()));
        }
        if (this.entities.size() > 0)
            System.out.println("[TRACER] Found " + this.entities.size() + " lost entities!");
        (new CheckEntityAnswSC(Ints.toArray(this.entities.keySet()))).sendToServer();
    }

    public Side getProcessSide() {
        return Side.CLIENT;
    }

    public String packetUniqueName() {
        return "openeye_entity_sc";
    }
}

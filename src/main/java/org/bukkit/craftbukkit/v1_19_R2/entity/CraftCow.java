package org.bukkit.craftbukkit.v1_19_R2.entity;

import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.entity.Cow;


public class CraftCow extends CraftAnimals implements Cow {

    public CraftCow(CraftServer server, net.minecraft.world.entity.animal.Cow entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.Cow getHandle() {
        return (net.minecraft.world.entity.animal.Cow) entity;
    }

    @Override
    public String toString() {
        return "CraftCow";
    }
}

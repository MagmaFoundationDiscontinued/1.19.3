package org.bukkit.craftbukkit.v1_19_R2.entity;

import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.entity.Ravager;

public class CraftRavager extends CraftRaider implements Ravager {

    public CraftRavager(CraftServer server, net.minecraft.world.entity.monster.Ravager entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.monster.Ravager getHandle() {
        return (net.minecraft.world.entity.monster.Ravager) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftRavager";
    }
}

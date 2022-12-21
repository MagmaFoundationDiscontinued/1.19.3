package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.WaterMob;

public class CraftWaterMob extends CraftCreature implements WaterMob {

    public CraftWaterMob(CraftServer server, WaterAnimal entity) {
        super(server, entity);
    }

    @Override
    public WaterAnimal getHandle() {
        return (WaterAnimal) entity;
    }

    @Override
    public String toString() {
        return "CraftWaterMob";
    }
}

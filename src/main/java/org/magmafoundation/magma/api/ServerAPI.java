/*
 * Magma Server
 * Copyright (C) 2019-2022.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.magmafoundation.magma.api;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.magmafoundation.magma.common.MagmaConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServerAPI
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 19/08/2019 - 04:47 pm
 */
public class ServerAPI {

    public static Map<String, String> commands = new ConcurrentHashMap<>();
    public static Map<EntityType<?>, String> entityTypeMap = new ConcurrentHashMap<>();

    /**
     * How many mods are loaded.
     *
     * @return int - loaded mods.
     */
    public static int getModSize() {
        return MagmaConstants.mods.get("mods") == null ? 0 : MagmaConstants.mods.get("mods");
    }

    /**
     * List all loaded mods by name.
     *
     * @return String - List of mods.
     */
    public static String getModList() {
        return MagmaConstants.modList.toString();
    }

    /**
     * Checks if a mod is in the list.
     *
     * @param modid for the mod to check.
     * @return boolean - if it's in the list or not.
     */
    public static boolean hasMod(String modid) {
        return getModList().contains(modid);
    }

    /**
     * Gets the Minecraft Server instance.
     *
     * @return MinecraftServer instance.
     */
    public static MinecraftServer getNMSServer() {
        return MinecraftServer.getServer();
    }

    /**
     * Gets the CraftBukkit Server
     *
     * @return CraftServer instance.
     */
    public static CraftServer getCBServer() {
        return (CraftServer) Bukkit.getServer();
    }

}

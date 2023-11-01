package com.paiique.pluginstudying.registers;

import com.paiique.pluginstudying.terrain.ModifyTerrainOnWalk;
import org.bukkit.plugin.Plugin;

public class BukkitRunnableInit {
    public BukkitRunnableInit(Plugin plugin) {
        new ModifyTerrainOnWalk().init(plugin);
    }
}

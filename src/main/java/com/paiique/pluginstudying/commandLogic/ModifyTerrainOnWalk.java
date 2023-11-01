package com.paiique.pluginstudying.commandLogic;

import com.paiique.pluginstudying.commandLogic.util.ParticleUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class ModifyTerrainOnWalk {
    private static boolean playerCanModifyTerrain = false;

    public void init(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {

                if (!playerCanModifyTerrain) {
                    this.cancel();
                }

                try {
                    Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
                    for (Player player : players) {
                        Location playerLocation = player.getLocation();

                        Location location = new Location(player.getWorld(), playerLocation.x(), playerLocation.y() - 0.2, playerLocation.z());
                        Material blockBelowPlayer = location.getBlock().getType();
                        if (blockBelowPlayer != Material.AIR && blockBelowPlayer != Material.DIAMOND_BLOCK && location.getBlock().isCollidable()) {

                            ParticleUtil.createRedstoneParticle(Color.BLUE, new Location(player.getWorld(),
                                    location.getBlockX() + 0.5, location.getBlockY() + 1,
                                    location.getBlockZ() + 0.5), 60, 0.5, 0, 0.5);

                            location.getBlock().setType(Material.DIAMOND_BLOCK);
                        }
                    }
                } catch (Exception e) {
                    plugin.getServer().sendMessage(Component.text("Error: " + e.getMessage()));
                    plugin.getServer().sendMessage(Component.text(ModifyTerrainOnWalk.class.getName() + " was disabled."));
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 1, 0);

    }

    public static void setPlayerCanModifyTerrain(boolean bool) {
         playerCanModifyTerrain = bool;
    }

    public static boolean getPlayerCanModifyTerrain() {
        return playerCanModifyTerrain;
    }

}

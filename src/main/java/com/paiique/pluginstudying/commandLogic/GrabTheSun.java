package com.paiique.pluginstudying.commandLogic;
import com.paiique.pluginstudying.commandLogic.util.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.UUID;

public class GrabTheSun {
    private static boolean playerIsHoldingTheSun = false;
    private static UUID playerUUID;


    public void init(Plugin plugin) {


        new BukkitRunnable() {
            long timeToSet;
            @Override
            public void run() {

                if (!playerIsHoldingTheSun) {
                    this.cancel();
                }

            Player player = plugin.getServer().getPlayer(playerUUID);


                double getPlayerCamY = player.getLocation().getDirection().getY();
                double getPlayerCamSide = player.getFacing().getDirection().getX();

                if (getPlayerCamSide == -1.0) {
                    timeToSet = (long) (( (getPlayerCamY > 0.73 ? getPlayerCamY + 0.05 : getPlayerCamY)  * -1 + 2.995) * 4000 + 800);

                } else {
                    timeToSet = (long) ((getPlayerCamY) * 4000 - 800);
                }

                System.out.println(getPlayerCamY);
                player.sendMessage(String.valueOf(timeToSet));
                player.getWorld().setTime(timeToSet);
                ParticleUtil.createParticle(player.getLocation(), Particle.ASH, 100, 5, 5, 5);
                ParticleUtil.createParticle(new Location(player.getWorld() ,player.getX(), player.getY(), player.getZ()),
                        Particle.DRIP_LAVA, 50, 3, 0, 3);

            }

        }.runTaskTimer(plugin, 1, 1);
    }

    public static boolean getPlayerIsHoldingTheSun() {
        return playerIsHoldingTheSun;
    }

    public static void setPlayerIsHoldingTheSun(boolean bool) {
        playerIsHoldingTheSun = bool;
    }

    public static void setPlayerUUID(UUID uuid) {
        playerUUID = uuid;
    }
}

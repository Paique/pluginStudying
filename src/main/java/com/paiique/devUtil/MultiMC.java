package com.paiique.devUtil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiMC {

    private Plugin plugin;
    private FileConfiguration internalConfig;
    public static Process process;

    public MultiMC(Plugin plugin) {
        this.plugin = plugin;
        internalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource("minecraft-dev.yml")));
    }
    public void startClient() {

        if (internalConfig.getBoolean("debug")) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(internalConfig.getString("MultiMCPath") + "/MultiMC.exe", "-l", internalConfig.getString("MultiMCInstance"), "--server", internalConfig.getString("IpToConnect"));
            try {
                process = processBuilder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            new BukkitRunnable() {
                        @Override
                        public void run() {

                            if (!process.isAlive()) {
                             plugin.getServer().shutdown();
                            }
                        }
                    }.runTaskTimer(plugin, 2, 1);
    }
}

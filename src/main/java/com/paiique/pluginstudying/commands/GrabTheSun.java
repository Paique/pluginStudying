package com.paiique.pluginstudying.commands;

import com.paiique.pluginstudying.terrain.ModifyTerrainOnWalk;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static com.paiique.pluginstudying.terrain.ModifyTerrainOnWalk.*;

public class GrabTheSun implements CommandExecutor {
    Plugin plugin = null;
    public GrabTheSun(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {



            if (strings[0].equals("enable")) {

                if (getPlayerCanModifyTerrain()) {
                    commandSender.sendMessage("You are already holding the sun, oh it's so hot!");
                } else {
                    setPlayerCanModifyTerrain(true);
                    new ModifyTerrainOnWalk().init(plugin);
                    commandSender.sendMessage("Holding the sun!");
                }
                return true;

            } else if (strings[0].equals("disable")) {

                if (!getPlayerCanModifyTerrain()) {
                    commandSender.sendMessage("You are not holding the sun! At least yet.");
                } else {
                    setPlayerCanModifyTerrain(false);
                    commandSender.sendMessage("You released the sun, so much better!");
                }

                return true;

            } else if (strings.length > 1) {
                commandSender.sendMessage("Too many arguments");
            } else {
                commandSender.sendMessage("Comando inválido");
            }
        }
        return false;
    }
}

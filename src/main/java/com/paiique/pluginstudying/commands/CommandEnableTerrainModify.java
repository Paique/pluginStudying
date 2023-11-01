package com.paiique.pluginstudying.commands;

import com.paiique.pluginstudying.commandLogic.ModifyTerrainOnWalk;
import com.paiique.pluginstudying.commands.tabCompleter.EnableTerrainModifyAutoComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static com.paiique.pluginstudying.commandLogic.ModifyTerrainOnWalk.*;

public class CommandEnableTerrainModify implements CommandExecutor {
    Plugin plugin = null;
    public CommandEnableTerrainModify(PluginCommand playerterrain, Plugin plugin) {
        this.plugin = plugin;
        playerterrain.setTabCompleter(new EnableTerrainModifyAutoComplete(plugin));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            commandSender.sendMessage(strings[0]);

            if (strings[0].equals("enable")) {

                if (getPlayerCanModifyTerrain()) {
                    commandSender.sendMessage("Terrain modification already enabled");
                } else {
                    setPlayerCanModifyTerrain(true);
                    new ModifyTerrainOnWalk().init(plugin);
                    commandSender.sendMessage("Terrain modification enabled");
                }
                return true;

            } else if (strings[0].equals("disable")) {

                if (!getPlayerCanModifyTerrain()) {
                    commandSender.sendMessage("Terrain modification already disabled");
                } else {
                    setPlayerCanModifyTerrain(false);
                    commandSender.sendMessage("Terrain modification disabled");
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

package com.paiique.pluginstudying.registers;

import com.paiique.pluginstudying.PluginStudying;
import com.paiique.pluginstudying.commands.CommandEnableTerrainModify;
import com.paiique.pluginstudying.commands.CommandGrabTheSun;
import org.bukkit.command.PluginCommand;

public class RegisterCommands {
    public RegisterCommands(PluginStudying plugin) {

        PluginCommand playerterrain = plugin.getCommand("playerterrain");
        playerterrain.setExecutor(new CommandEnableTerrainModify(playerterrain ,plugin));

        PluginCommand grabthesun = plugin.getCommand("grabthesun");
        grabthesun.setExecutor(new CommandGrabTheSun(grabthesun, plugin));

    }
}

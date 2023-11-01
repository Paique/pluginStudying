package com.paiique.pluginstudying.registers;

import com.paiique.pluginstudying.PluginStudying;
import com.paiique.pluginstudying.commands.CommandEnableTerrainModify;
import com.paiique.pluginstudying.commands.tabCompleter.CommandEnableTerrainModifyAutoComplete;
import org.bukkit.command.PluginCommand;

public class RegisterCommands {
    public RegisterCommands(PluginStudying plugin) {
        PluginCommand getCommand = plugin.getCommand("playerterrain");

        getCommand.setTabCompleter(new CommandEnableTerrainModifyAutoComplete(plugin));
        getCommand.setExecutor(new CommandEnableTerrainModify(plugin));
    }
}

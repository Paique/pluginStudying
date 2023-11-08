package com.paiique.pluginstudying.registers;

import com.paiique.pluginstudying.PluginStudying;
import com.paiique.pluginstudying.commands.CommandEnableTerrainModify;
import com.paiique.pluginstudying.commands.CommandGrabTheSun;
import com.paiique.pluginstudying.commands.TestCommand;
import org.bukkit.command.PluginCommand;

public class RegisterCommands {
    private final String[] grabthesunCmds = new String[]{"grab", "release"};
    private final String[] switchCommand = new String[]{"enable", "disable"};

    public RegisterCommands(PluginStudying plugin) {

        PluginCommand playerterrain = plugin.getCommand("playerterrain");
        playerterrain.setExecutor(new CommandEnableTerrainModify(playerterrain, plugin, switchCommand));

        PluginCommand grabthesun = plugin.getCommand("grabthesun");
        grabthesun.setExecutor(new CommandGrabTheSun(grabthesun, plugin, grabthesunCmds));


        PluginCommand testCommand = plugin.getCommand("testcommand");
        testCommand.setExecutor(new TestCommand(testCommand, plugin, switchCommand));

    }
}

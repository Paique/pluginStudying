package com.paiique.pluginstudying.commands;

import com.paiique.pluginstudying.commandLogic.GrabTheSun;
import com.paiique.pluginstudying.commands.tabCompleter.GrabTheSunAutoComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static com.paiique.pluginstudying.commandLogic.GrabTheSun.*;

public class CommandGrabTheSun implements CommandExecutor {
    Plugin plugin = null;
    public CommandGrabTheSun(PluginCommand grabthesun, Plugin plugin) {
        this.plugin = plugin;
        grabthesun.setTabCompleter(new GrabTheSunAutoComplete(plugin));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (strings[0].equals("grab")) {

                if (getPlayerIsHoldingTheSun()) {
                    commandSender.sendMessage("You are already holding the sun, oh it's so hot!");
                } else {
                    setPlayerIsHoldingTheSun(true);
                    setPlayerUUID(player.getUniqueId());
                    new GrabTheSun().init(plugin);
                    commandSender.sendMessage(  "WHAT ARE YOU DOING IT'S HOT AS HELL!");
                }
                return true;

            } else if (strings[0].equals("release")) {

                if (!getPlayerIsHoldingTheSun()) {
                    commandSender.sendMessage("You are not holding the sun! Don't do that.");
                } else {
                    setPlayerIsHoldingTheSun(false);
                    commandSender.sendMessage("You released the sun, it was so scary, PLEASE NEVER DO THAT AGAIN!");
                }

                return true;

            } else if (strings.length > 1) {
                commandSender.sendMessage("Too many arguments");
            } else {
                commandSender.sendMessage("Invalid arguments.");
            }
        }
        return false;
    }
}

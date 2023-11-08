package com.paiique.pluginstudying.commandLogic.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandTab implements TabCompleter {
    private String[] subcommands;
    private Plugin plugin;
    public CommandTab(Plugin plugin, String[] subcommands) {
        this.plugin = plugin;
        this.subcommands = subcommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> ret = new ArrayList<>();

        for (String subCmd : subcommands) {
            ret.add(subCmd);
        }
        return StringUtil.copyPartialMatches(strings[0].toLowerCase(), ret, new ArrayList<>());
    }

}

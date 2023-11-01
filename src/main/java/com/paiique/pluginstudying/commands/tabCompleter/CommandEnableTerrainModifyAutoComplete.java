package com.paiique.pluginstudying.commands.tabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.*;

public class CommandEnableTerrainModifyAutoComplete implements TabCompleter {
    private List<String> subcommands = new ArrayList<>(Arrays.asList("enable","disable"));

    Plugin plugin = null;
    public CommandEnableTerrainModifyAutoComplete(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete( CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> ret = new ArrayList<>();

            for (String subCmd : subcommands) {
                ret.add(subCmd);
            }
            return StringUtil.copyPartialMatches(strings[0].toLowerCase(), ret, new ArrayList<>());
        }
}


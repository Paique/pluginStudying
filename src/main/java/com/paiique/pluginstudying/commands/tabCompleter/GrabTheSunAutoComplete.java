package com.paiique.pluginstudying.commands.tabCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrabTheSunAutoComplete implements TabCompleter {
    private List<String> subcommands = new ArrayList<>(Arrays.asList("grab","release"));
    Plugin plugin = null;
    public GrabTheSunAutoComplete(Plugin plugin) {
        this.plugin = plugin;
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

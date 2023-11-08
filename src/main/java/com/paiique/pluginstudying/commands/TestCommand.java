package com.paiique.pluginstudying.commands;

import com.paiique.pluginstudying.commandLogic.CustomArmorStand;
import com.paiique.pluginstudying.commandLogic.util.CommandTab;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class TestCommand implements CommandExecutor {
    ArrayList<ArmorStand> armorStands = new ArrayList<>();
    ArrayList<BukkitRunnable> tasks = new ArrayList<>();
    Plugin plugin;
    public TestCommand(PluginCommand pluginCommand, Plugin plugin, String[] commandList) {

        this.plugin = plugin;
        pluginCommand.setTabCompleter(new CommandTab(plugin, commandList));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = ((Player) commandSender);

        if (strings[0].equalsIgnoreCase("spawn")){
            ArmorStand armorStand = CustomArmorStand.spawn(player.getLocation());
            armorStands.add(armorStand);

             new BukkitRunnable() {

                 {
                 tasks.add(this);
                 }

                @Override
                public void run() {

                     if (player.getItemInHand().getItemMeta().getDisplayName().equals("minecraft:arrow") ) {
                         @Nullable Block loc = player.getTargetBlock(10);
                         if (loc != null) {
                         armorStand.teleport(new Location(player.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ()));
                         }
                     }
                }
            }.runTaskTimer(plugin ,0, 0);


            int armorStandId = 0;
            for (ArmorStand armstd: armorStands) {
                if (armstd.equals(armorStand)) {
                    player.sendMessage("ArmorStand with ID " + armorStandId + " spawned.");
                    armorStand.setCustomName("Armor_Stand_" + armorStandId);
                    break;
                }
                armorStandId++;
            }
            return true;
        }


        if (strings[0].equals("remove")) {
            try {

                ArmorStand armorStandToRemove = armorStands.get(Integer.parseInt(strings[1]));
                armorStands.remove(Integer.parseInt(strings[1]));
                tasks.get(Integer.parseInt(strings[1])).cancel();
                armorStandToRemove.remove();
            return true;
            } catch (NumberFormatException e) {
                player.sendMessage("Error: \"" +  strings[1] + "\" is not a number.");
            } catch (IndexOutOfBoundsException e) {
                player.sendMessage("ArmorStand \"" + strings[1] + "\" does not exist.");
            }
        }


        return false;
    }
}

package me.awesomepandapig.fakefill;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class FakeFill extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("fakefill")) {
            if (!sender.hasPermission("fakefill.use")) {
                sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command" +
                        ". Please contact the server administrators if you believe that this is in error.");
                return true;
            } else if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&bFake Fill&3] &7 " +
                        "Fake Fill version 1.0. For help, please use /fakefill help"));
                return true;
            } else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&bFake Fill Help&3]\n&6/fset: " +
                        "&fChanges the appearance of a single block\n&6/ffill: &fChanges the appearance of all blocks" +
                        " " +
                        "within a selection\n&6/freplace: &fChanges the appearance of certain blocks within a " +
                        "selection"));
            }
        }

        if (label.equalsIgnoreCase("fset")) {
            if (!sender.hasPermission("fakefill.use")) {
                sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command" +
                        ". Please contact the server administrators if you believe that this is in error.");
                return true;
            } else if (args.length < 5) {
                sender.sendMessage(ChatColor.RED + "Usage: /fset <coordinate> <itemID> <colorID>");
            } else {
                for (Player player : getServer().getOnlinePlayers()) {
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    int colorID = Integer.parseInt(args[4]);
                    Location loc = new Location(player.getWorld(), x, y, z);
                    try {
                        player.sendBlockChange(loc, Material.getMaterial(args[3].toUpperCase()), (byte) colorID);
                    }
                    catch(Exception e) {
                        sender.sendMessage(ChatColor.RED + "Usage: /fset <coordinate> <itemID> <colorID>");
                    }
                }
            }
        }

        if (label.equalsIgnoreCase("ffill")) {
            if (!sender.hasPermission("fakefill.use")) {
                sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command" +
                        ". Please contact the server administrators if you believe that this is in error.");
                return true;
            } else if (args.length < 8) {
                sender.sendMessage(ChatColor.RED + "Usage: /ffill <coordinate> <coordinate> <itemID> <colorID>");
            } else {
                for (Player player : getServer().getOnlinePlayers()) {
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    double endingx = Double.parseDouble(args[3]);
                    double endingy = Double.parseDouble(args[4]);
                    double endingz = Double.parseDouble(args[5]);
                    int colorID = Integer.parseInt(args[7]);
                    if (args[7].equals("")) {
                        colorID = 0;
                    }

                    double maxX = 0;
                    double minX = 0;
                    if (x > endingx && x != endingx) {
                        maxX = x;
                        minX = endingx;
                    } else {
                        maxX = endingx;
                        minX = x;
                    }

                    double maxY = 0;
                    double minY = 0;
                    if (y > endingy && y != endingy) {
                        maxY = y;
                        minY = endingy;
                    } else {
                        maxY = endingy;
                        minY = y;
                    }

                    double maxZ = 0;
                    double minZ = 0;
                    if (z > endingz && z != endingz) {
                        maxZ = z;
                        minZ = endingz;
                    } else {
                        maxZ = endingz;
                        minZ = z;
                    }

                    //argument 0 = x;
                    //argument 1 = y;
                    //argument 2 = z;
                    //argument 3 = endingx;
                    //argument 4 = endingy;
                    //argument 5 = endingz;
                    //argument 6 = desired material;
                    //argument 7 = desired color;


                    for (double j = minX; j <= maxX; j++) {
                        for (double k = minY; k <= maxY; k++) {
                            for (double l = minZ; l <= maxZ; l++) {
                                Location loc = new Location(player.getWorld(), j, k, l);
                                try {
                                    player.sendBlockChange(loc, Material.getMaterial(args[6].toUpperCase()), (byte) colorID);
                                }
                                catch(Exception e) {
                                    sender.sendMessage(ChatColor.RED + "Usage: /ffill <coordinate> <" +
                                            "coordinate>" +
                                            "<itemID>" +
                                            "<colorID>");
                                }

                            }
                        }
                    }
                }
            }
        }

        if (label.equalsIgnoreCase("freplace")) {
            if (!sender.hasPermission("fakefill.use")) {
                sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command" +
                        ". Please contact the server administrators if you believe that this is in error.");
                return true;
            } else if (args.length < 10) {
                sender.sendMessage(ChatColor.RED + "Usage: /freplace <coordinate> <coordinate> " +
                        "<itemID> <colorID> <itemID> <colorID>");
            } else {
                for (Player player : getServer().getOnlinePlayers()) {
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);
                    double endingx = Double.parseDouble(args[3]);
                    double endingy = Double.parseDouble(args[4]);
                    double endingz = Double.parseDouble(args[5]);
                    int colorID = Integer.parseInt(args[9]);
                    if (args[9].equals("")) {
                        colorID = 0;
                    }

                    int unwantedColorID = Integer.parseInt(args[7]);
                    if (args[7].equals("")) {
                        unwantedColorID = 0;
                    }

                    double maxX = 0;
                    double minX = 0;
                    if (x > endingx && x != endingx) {
                        maxX = x;
                        minX = endingx;
                    } else {
                        maxX = endingx;
                        minX = x;
                    }

                    double maxY = 0;
                    double minY = 0;
                    if (y > endingy && y != endingy) {
                        maxY = y;
                        minY = endingy;
                    } else {
                        maxY = endingy;
                        minY = y;
                    }

                    double maxZ = 0;
                    double minZ = 0;
                    if (z > endingz && z != endingz) {
                        maxZ = z;
                        minZ = endingz;
                    } else {
                        maxZ = endingz;
                        minZ = z;
                    }

                    //argument 0 = x;
                    //argument 1 = y;
                    //argument 2 = z;
                    //argument 3 = endingx;
                    //argument 4 = endingy;
                    //argument 5 = endingz;
                    //argument 6 = unwanted material;
                    //argument 7 = unwanted color;
                    //argument 8 = wanted material;
                    //argument 9 = wanted color;

                    for (double j = minX; j <= maxX; j++) {
                        for (double k = minY; k <= maxY; k++) {
                            for (double l = minZ; l <= maxZ; l++) {
                                Location loc = new Location(player.getWorld(), j, k, l);
                                if (loc.getBlock().getType().equals(Material.getMaterial(args[6].toUpperCase())) && loc.getBlock().getData() == (byte) unwantedColorID) {
                                    try {
                                        player.sendBlockChange(loc, Material.getMaterial(args[8].toUpperCase()),
                                                (byte) colorID);
                                    }
                                    catch(Exception e) {
                                        sender.sendMessage(ChatColor.RED + "Usage: /freplace <coordinate> <coordinate> " +
                                                "<itemID> <colorID> <itemID> <colorID>");
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }


        return false;
    }
}

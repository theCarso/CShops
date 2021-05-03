package me.thecarso.cshops;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Format {

    public static String colorize(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void sendMessage(CommandSender player, String string){
        player.sendMessage(colorize(string));
    }
}

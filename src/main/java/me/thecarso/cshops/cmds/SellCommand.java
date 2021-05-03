package me.thecarso.cshops.cmds;

import me.thecarso.cshops.CShops;
import me.thecarso.cshops.Format;
import me.thecarso.cshops.utils.SellManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Format.sendMessage(sender, "&cYou can't do that.");
            return true;
        }

        if (args.length == 1) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("hand")) {
                SellManager.Result results = CShops.getSellManager().sellPlayersHand(player);
                if (results.getMoney() > 0) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_CHIME, 1,1);
                    Format.sendMessage(sender, "&dYou sold &5" + results.getItems() + "x &ditems for &5" + CShops.getEcon().format(results.getMoney()) + "&d.");
                } else {
                    Format.sendMessage(sender, "&cYou cannot sell this item.");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("all")) {
                SellManager.Result results = CShops.getSellManager().sellPlayersHandAll(player);
                if (results.getMoney() > 0) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_CHIME, 1,1);
                    Format.sendMessage(sender, "&dYou sold &5" + results.getItems() + "x &ditems for &5" + CShops.getEcon().format(results.getMoney()) + "&d.");
                } else {
                    Format.sendMessage(sender, "&cYou cannot sell this item.");
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("inv") || args[0].equalsIgnoreCase("inventory")) {
                SellManager.Result results = CShops.getSellManager().sellPlayersAll(player);
                if (results.getMoney() > 0) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_CHIME, 1,1);
                    Format.sendMessage(sender, "&dYou sold &5" + results.getItems() + "x &ditems for &5" + CShops.getEcon().format(results.getMoney()) + "&d.");
                } else {
                    Format.sendMessage(sender, "&cYou have no sellable items.");
                }
                return true;
            }
        }
        Format.sendMessage(sender, "&cInvalid arguments; /sell <hand;all;inv>");
        return true;
    }
}

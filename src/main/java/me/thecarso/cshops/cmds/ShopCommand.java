package me.thecarso.cshops.cmds;

import me.thecarso.cshops.CShops;
import me.thecarso.cshops.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Format.sendMessage(sender, "&cYou can't do that.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            CShops.getShopManager().openShopGUI(player);
            return true;
        }

        if (args.length == 1) {
            if (CShops.getShopManager().getCategories().containsKey(args[0].toLowerCase())) {
                CShops.getShopManager().openShopGUI(player, args[0]);
                return true;
            }
            Format.sendMessage(sender, "&cInvalid category.");
            return true;
        }
        Format.sendMessage(sender, "&cInvalid arguments. /shop OR /shop [category]");
        return true;
    }
}

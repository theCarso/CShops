package me.thecarso.cshops.shops;

import me.thecarso.cshops.Format;
import me.thecarso.guiapi.ItemBuilder;
import me.thecarso.guiapi.guis.Menu;
import me.thecarso.guiapi.guis.MenuAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemMenu extends Menu {

    ShopItem item;

    public ItemMenu(ShopItem item, Player player) {
        super(player);
        this.item = item;
    }

    @Override
    public String getMenuName() {
        return "Purchasing: " + item.getType().name();
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void setup() {
        for (int i : item.getAmounts()) {
            ItemStack displayItem = item.getItem(i);
            ItemMeta meta = displayItem.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.RED + "");
            lore.add(ChatColor.translateAlternateColorCodes('&', "&aPrice: $" + (item.getBuyPrice() * i)));
            meta.setLore(lore);
            displayItem.setItemMeta(meta);

            addItem(displayItem, new MenuAction() {
                @Override
                public void leftClick() {
                    if(item.buy(player, i)){
                        Format.sendMessage(player, "&aYour purchase of " + i + "x " + item.getType().name() + " is complete!");
                    }else{
                        Format.sendMessage(player, "&cInsufficient funds.");
                    }
                }

                @Override
                public void rightClick() {
                    if(item.buy(player, i)){
                        Format.sendMessage(player, "&aYour purchase of " + i + "x " + item.getType().name() + " is complete!");
                    }else{
                        Format.sendMessage(player, "&cInsufficient funds.");
                    }
                }

                @Override
                public void middleClick() {
                    if(item.buy(player, i)){
                        Format.sendMessage(player, "&aYour purchase of " + i + "x " + item.getType().name() + " is complete!");
                    }else{
                        Format.sendMessage(player, "&cInsufficient funds.");
                    }
                    player.closeInventory();
                }
            });
        }
    }
}

package me.thecarso.cshops.shops;

import lombok.Getter;
import me.thecarso.cshops.CShops;
import me.thecarso.guiapi.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ShopItem {

    private @Getter
    Material type;
    byte data;
    private @Getter
    ArrayList<Integer> amounts;
    private @Getter
    double buyPrice;

    public ShopItem(Material type, byte data, ArrayList<Integer> amounts, double buyPrice) {
        this.type = type;
        this.data = data;
        this.amounts = amounts;
        this.buyPrice = buyPrice;
    }

    public boolean buy(Player player, int amount) {
        double price = amount * buyPrice;
        if (CShops.getEcon().getBalance(player) >= price) {
            CShops.getEcon().withdrawPlayer(player, price);
            ItemStack itemStack = getItem(amount);
            player.getInventory().addItem(itemStack);
            return true;
        }
        return false;
    }

    public ItemStack getItem(int amount) {
        ItemStack itemStack = new ItemBuilder(type, amount).setDurability(data).build();
        return itemStack;
    }
}

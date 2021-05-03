package me.thecarso.cshops.utils;

import me.thecarso.cshops.CFile;
import me.thecarso.cshops.CShops;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SellManager {

    CFile sellFile;
    HashMap<Material, Double> values;

    public SellManager() {
        sellFile = new CFile(CShops.getInstance(), "sell", true);
        values = new HashMap<>();
        loadValues();
    }

    private void loadValues() {
        for (String string : sellFile.getFile().getConfigurationSection("values").getKeys(false)) {
            values.put(Material.valueOf(string), sellFile.getFile().getDouble("values." + string));
        }
    }

    public Result sellPlayersHand(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack != null) {
            Material m = itemStack.getType();
            if (values.containsKey(m)) {
                double money = itemStack.getAmount() * values.get(m);
                int amount = itemStack.getAmount();
                player.getInventory().setItem(player.getInventory().getHeldItemSlot(), null);
                CShops.getEcon().depositPlayer(player, money);
                return new Result(amount, money);
            }
        }
        return new Result(0, 0);
    }

    public Result sellPlayersHandAll(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack != null) {
            Material m = itemStack.getType();
            if (values.containsKey(m)) {
                int amount = 0;
                for (int i = 0; i < 36; i++) {
                    ItemStack invItem = player.getInventory().getItem(i);
                    if (invItem != null && invItem.getType() == m) {
                        amount += player.getInventory().getItem(i).getAmount();
                        player.getInventory().setItem(i, null);
                    }
                }
                double money = amount * values.get(m);
                CShops.getEcon().depositPlayer(player, money);
                return new Result(amount, money);
            }
        }
        return new Result(0,0);
    }

    public Result sellPlayersAll(Player player) {
        double money = 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && values.containsKey(item.getType())) {
                amount +=item.getAmount();
                money += item.getAmount() * values.get(item.getType());
                player.getInventory().setItem(i, null);
            }
        }
        if (money > 0) {
            CShops.getEcon().depositPlayer(player, money);
            return new Result(amount, money);
        }
        return new Result(0, 0);
    }

    public class Result {
        private final int items;
        private final double money;

        public Result(int items, double money) {
            this.items = items;
            this.money = money;
        }

        public int getItems() {
            return items;
        }

        public double getMoney() {
            return money;
        }
    }
}

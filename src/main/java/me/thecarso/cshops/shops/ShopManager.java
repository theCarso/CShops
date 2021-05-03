package me.thecarso.cshops.shops;

import lombok.Getter;
import me.thecarso.cshops.CFile;
import me.thecarso.cshops.CShops;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ShopManager {

    private @Getter
    HashMap<String, Category> categories;
    private @Getter
    TreeMap<Integer, Category> categoriesByWeight;

    public ShopManager() {
        categories = new HashMap<>();
        categoriesByWeight = new TreeMap<>();
        File folder = new File(CShops.getInstance().getDataFolder(), "shops");
        if (!folder.exists()) {
            folder.mkdir();
            new CFile(CShops.getInstance(), "shops/blocks", true).saveFile();
        }
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            String catName = file.getName().replace(".yml", "");
            CFile catFile = new CFile(CShops.getInstance(), "shops/" + catName, false);
            Category cat = new Category(catName, catFile);
            categories.put(catName.toLowerCase(), cat);
            categoriesByWeight.put(catFile.getFile().getInt("weight"), cat);
        }

    }

    public void openShopGUI(Player player) {
        new ShopMenu(player).open();
    }

    public void openShopGUI(Player player, String string) {
        Category category = categories.get(string.toLowerCase());
        new CategoryMenu(category, 1, player).open();
    }
}

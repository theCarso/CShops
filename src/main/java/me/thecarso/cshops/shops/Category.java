package me.thecarso.cshops.shops;

import lombok.Getter;
import me.thecarso.cshops.CFile;
import me.thecarso.cshops.CShops;
import me.thecarso.guiapi.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Category {

    private @Getter
    ArrayList<ShopItem> shopItems;
    private String id;
    private @Getter
    String display;

    ItemStack displayItem;

    public Category(String id, CFile file) {
        this.id = id;
        shopItems = new ArrayList<>();
        ConfigurationSection section = file.getFile();
        ItemBuilder builder = new ItemBuilder(Material.valueOf(section.getString("display.type"))).setName(section.getString("display.name"));
        for (String string : section.getStringList("display.lore")) {
            builder.addLoreLine(string);
        }
        displayItem = builder.build();
        display = displayItem.getItemMeta().getDisplayName();

        section = section.getConfigurationSection("items");
        for (String string : section.getKeys(false)) {
            shopItems.add(new ShopItem(Material.valueOf(section.getString(string + ".type")),
                    (byte) section.getInt(string + ".data"),
                    splitIntList(section.getString(string + ".amounts")),
                    section.getDouble(string + ".buyPrice")));
        }
    }

    private static ArrayList<Integer> splitIntList(String input) {
        ArrayList<Integer> output = new ArrayList<>();
        for (String s : input.split(",")) {
            output.add(Integer.parseInt(s));
        }
        return output;
    }

}

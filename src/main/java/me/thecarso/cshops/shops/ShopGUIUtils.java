package me.thecarso.cshops.shops;

import me.thecarso.guiapi.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShopGUIUtils {

    public static ItemStack filler = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability(7).setName("&6 ").build();
    public static int[] borders27 = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};
    public static int[] borders54 = {0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 17,
            18, 26,
            27, 35,
            36, 44,
            45, 46, 47, 48, 49, 50, 51, 52, 53};

    public static ItemStack closeItem = new ItemBuilder(Material.BARRIER).setName("&cClose Shop").build();
    public static ItemStack lastPageItem = new ItemBuilder(Material.STONE_BUTTON).setName("&aLast Page").build();
    public static ItemStack nextPageItem = new ItemBuilder(Material.STONE_BUTTON).setName("&aNext Page").build();

}

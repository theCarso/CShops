package me.thecarso.cshops.shops;

import me.thecarso.cshops.CShops;
import me.thecarso.guiapi.ItemBuilder;
import me.thecarso.guiapi.guis.Menu;
import me.thecarso.guiapi.guis.MenuAction;
import org.bukkit.entity.Player;

public class ShopMenu extends Menu {
    public ShopMenu(Player player) {
        super(player);
    }

    @Override
    public String getMenuName() {
        return "Server Shop";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void setup() {
        for (int i : ShopGUIUtils.borders27) {
            addItem(ShopGUIUtils.filler, i);
        }
        for (Category category : CShops.getShopManager().getCategoriesByWeight().values()) {
            addItem(category.displayItem, new MenuAction() {
                @Override
                public void leftClick() {
                    new CategoryMenu(category, 1, player).open();
                }

                @Override
                public void rightClick() {
                    new CategoryMenu(category, 1, player).open();
                }

                @Override
                public void middleClick() {
                    new CategoryMenu(category, 1, player).open();
                }
            });
        }
    }
}

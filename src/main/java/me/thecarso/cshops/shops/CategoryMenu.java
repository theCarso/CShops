package me.thecarso.cshops.shops;

import me.thecarso.guiapi.ItemBuilder;
import me.thecarso.guiapi.guis.Menu;
import me.thecarso.guiapi.guis.MenuAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CategoryMenu extends Menu {

    Category category;
    int page;

    public CategoryMenu(Category category, int page, Player player) {
        super(player);
        this.category = category;
        this.page = page;
    }

    @Override
    public String getMenuName() {
        return ChatColor.stripColor(category.getDisplay() + " (" + page + "/" + (int)Math.ceil(category.getShopItems().size() / 28.0) + ")");
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setup() {
        for (int i : ShopGUIUtils.borders54) {
            addItem(ShopGUIUtils.filler, i);
        }
        addItem(ShopGUIUtils.closeItem, 49, new MenuAction() {
            @Override
            public void leftClick() {
                player.closeInventory();
            }

            @Override
            public void rightClick() {
                player.closeInventory();

            }

            @Override
            public void middleClick() {
                player.closeInventory();

            }
        });

        // Back page check
        if (page > 1) {
            addItem(ShopGUIUtils.lastPageItem, 48, new MenuAction() {
                @Override
                public void leftClick() {
                    new CategoryMenu(category, page - 1, player).open();
                }

                @Override
                public void rightClick() {
                    new CategoryMenu(category, page - 1, player).open();
                }

                @Override
                public void middleClick() {
                    new CategoryMenu(category, page - 1, player).open();
                }
            });
        }

        // Next page check
        if (category.getShopItems().size() > (page * 28)) {
            addItem(ShopGUIUtils.nextPageItem, 50, new MenuAction() {
                @Override
                public void leftClick() {
                    new CategoryMenu(category, page + 1, player).open();
                }

                @Override
                public void rightClick() {
                    new CategoryMenu(category, page + 1, player).open();
                }

                @Override
                public void middleClick() {
                    new CategoryMenu(category, page + 1, player).open();
                }
            });
        }

        int startIndex = (page - 1) * 28;
        for (int i = startIndex; i < Math.min((startIndex + 28), category.getShopItems().size()); i++) {
            ShopItem shopItem = category.getShopItems().get(i);
            ItemStack item = new ItemBuilder(shopItem.getType()).setDurability(shopItem.data)
                    .addLoreLine("&eBuy Price: &6$" + shopItem.getBuyPrice())
                    .addLoreLine("&6")
                    .addLoreLine("&aClick to see purchase options").build();
            addItem(item, new MenuAction() {
                @Override
                public void leftClick() {
                    new ItemMenu(shopItem, player).open();
                }

                @Override
                public void rightClick() {
                    new ItemMenu(shopItem, player).open();
                }

                @Override
                public void middleClick() {
                    new ItemMenu(shopItem, player).open();
                }
            });
        }
    }
}

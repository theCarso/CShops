package me.thecarso.cshops;

import lombok.Getter;
import me.thecarso.cshops.cmds.SellCommand;
import me.thecarso.cshops.cmds.ShopCommand;
import me.thecarso.cshops.shops.ShopManager;
import me.thecarso.cshops.utils.SellManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class CShops extends JavaPlugin {

    private static @Getter
    CShops instance;
    private static @Getter
    CFile configFile;

    private static @Getter
    ShopManager shopManager;
    private static @Getter
    SellManager sellManager;
    private static @Getter Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;
        setupEconomy();
        configFile = new CFile(this, "config", true);

        shopManager = new ShopManager();
        sellManager = new SellManager();
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("sell").setExecutor(new SellCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}

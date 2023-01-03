package com.github.nicklib.utils;

import com.github.nicklib.data.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ShopUtil {

    private String shop;
    private Config config;


    public ShopUtil() {

    }


    public ShopUtil(JavaPlugin plugin) {
        this.config = new Config("shop/", plugin);
    }


    public ShopUtil(String shop, JavaPlugin plugin) {
        this.shop = shop;
        config = new Config("shop/" + shop, plugin);
    }


    /**
     * Creates a new shop
     */
    public void createShop() {
        config.getConfig().set(shop + ".title", shop);
        config.getConfig().set(shop + ".size", 6);
        config.getConfig().set(shop + ".item", new HashMap<>());
        config.saveConfig();
    }


    /**
     * delete the shop
     */
    public void deleteShop() {
        config.deleteFile();
    }


    /**
     * Check if Shop file exists
     *
     * @return true if exists
     */
    public Boolean isShopExist() {
        return config.isFileExist();
    }
}

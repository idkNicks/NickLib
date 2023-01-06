package com.github.nicklib.utils;

import com.github.nicklib.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class ShopUtil {

    private String shop;
    private Config config;
    private Player player;


    public ShopUtil() {
    }


    public ShopUtil(Player player) {
        this.player = player;
    }

    public ShopUtil(JavaPlugin plugin) {
        this.config = new Config("shop/", plugin);
    }


    public ShopUtil(String shop, JavaPlugin plugin) {
        this.shop = shop;
        config = new Config("shop/" + shop, plugin);
    }


    public ShopUtil(Player player, Config config) {
        this.player = player;
        this.config = config;
    }


    /**
     * Creates a new shop
     */
    public void createShop() {
        config.getConfig().set(shop + ".title", shop);
        config.getConfig().set(shop + ".size", 6);
        config.getConfig().set(shop + ".items", new HashMap<>());
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


    /**
     * save the inventory to the config
     *
     * @param section ConfigurationSection path
     * @param inv     shop inventory
     */
    @SuppressWarnings("all")
    public void saveInventory(InventoryClickEvent event, String section, @NotNull Inventory inv) {
        if (this.config.getConfig().getConfigurationSection(section + ".items") == null)
            this.config.getConfig().createSection(section + ".items");
        ConfigurationSection configurationSection = this.config.getConfig().getConfigurationSection(section + ".items");

        if (inv.isEmpty()) {
            this.config.getConfig().set(section + ".items", new HashMap<>());
            this.config.saveConfig();
            return;
        }

        ItemStack item = inv.getItem(event.getSlot());
        if (item != null) {
            ConfigurationSection itemSection = configurationSection.createSection(String.valueOf(event.getSlot()));
            itemSection.set("itemstack", item);
            this.config.saveConfig();

            itemSection.set("price.sell", 0);
            itemSection.set("price.buy", 0);
            this.config.saveConfig();
        }
    }


    /**
     * save the inventory to the config
     *
     * @param section ConfigurationSection path
     * @param inv     shop inventory
     */
    @SuppressWarnings("all")
    public void saveInventory(InventoryDragEvent event, String section, @NotNull Inventory inv) {
        if (this.config.getConfig().getConfigurationSection(section + ".items") == null)
            this.config.getConfig().createSection(section + ".items");
        ConfigurationSection configurationSection = this.config.getConfig().getConfigurationSection(section + ".items");

        if (inv.isEmpty()) {
            this.config.getConfig().set(section + ".items", new HashMap<>());
            this.config.saveConfig();
            return;
        }

        for (int i : event.getRawSlots()) {
            ItemStack item = inv.getItem(i);
            event.getInventory().setItem(i, item);

            if (item != null) {
                ConfigurationSection itemSection = configurationSection.createSection(String.valueOf(i));
                itemSection.set("itemstack", item);
                this.config.saveConfig();

                itemSection.set("price.sell", 0);
                itemSection.set("price.buy", 0);
                this.config.saveConfig();
            }
        }
    }


    /**
     * get the shop inventory from the config
     *
     * @param lores          Config lores
     * @param section        ConfigurationSection path
     * @param inventoryTitle shop inventory title
     * @param size           shop inventory size * 9 [1~6]
     * @param lore           shop inventory lore
     */
    public void getShopInventory(Config lores, String section, String inventoryTitle, Integer size, String lore) {

        Inventory inv = Bukkit.createInventory(null, size * 9, inventoryTitle);
        ConfigurationSection configurationSection = config.getConfig().getConfigurationSection(section + ".items");

        if (section != null) {
            for (String key : configurationSection.getKeys(false)) {

                ItemStack itemStack = new ItemStack(configurationSection.getItemStack(key + ".itemstack"));

                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setLore(Translate.color(lores.getConfig().getStringList(lore)));
                itemStack.setItemMeta(itemMeta);

                inv.setItem(Integer.parseInt(key), itemStack);
            }
        }
        player.openInventory(inv);
    }


    /**
     * get the shop inventory from the config
     *
     * @param event   InventoryClickEvent
     * @param lore    shop inventory lore
     */
    public void addLoreToItem(Config config, @NotNull InventoryClickEvent event, String lore) {

        ItemStack itemStack = event.getCurrentItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> loreList = config.getConfig().getStringList(lore);

        itemMeta.setLore(Translate.color(loreList));
        itemStack.setItemMeta(itemMeta);
    }


    /**
     * get the shop inventory from the config
     * @param event   InventoryClickEvent
     * @param lore    shop inventory lore
     */
    public void addLoreToItem(Config config, @NotNull InventoryDragEvent event, String lore) {

        ItemStack itemStack = new ItemStack(event.getOldCursor().getType(), 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> loreList = config.getConfig().getStringList(lore);

        itemMeta.setLore(Translate.color(loreList));
        itemStack.setItemMeta(itemMeta);

        for (int i : event.getRawSlots()) {
            event.getInventory().setItem(i, itemStack);
        }
    }


    /**
     * set Lore null
     * @param event  InventoryClickEvent
     */
    public void removeItemLoreNull(@NotNull InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(null);
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(event.getCurrentItem());
        event.getInventory().setItem(event.getSlot(), null);
    }



    public void setItemPrice(String section, String priceType, Integer slot, Integer price) {
        ConfigurationSection configurationSection = config.getConfig().getConfigurationSection(section + ".items");
        ConfigurationSection itemSection = configurationSection.getConfigurationSection(String.valueOf(slot));

        itemSection.set("price." + priceType, price);
        config.saveConfig();
    }



    /**
     * is the Shop Buy Price Already Set
     *
     * @param section path
     * @param key     ConfigurationSection
     * @return price
     */
    public boolean isAlreadyShopBuyPrice(String section, ConfigurationSection key) {
        return config.getConfig().get(section + ".items." + key + ".price.sell") != null;
    }


    /**
     * is the Shop Buy Price Already Set
     *
     * @param section path
     * @param key     ConfigurationSection
     * @return price
     */
    public boolean isAlreadyShopSellPrice(String section, ConfigurationSection key) {
        return config.getConfig().get(section + ".items." + key + ".price.sell") != null;
    }

}

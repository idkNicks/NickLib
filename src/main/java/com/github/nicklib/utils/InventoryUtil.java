package com.github.nicklib.utils;

import com.github.nicklib.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("all")
public class InventoryUtil {

    private Player player;
    private Config config;


    public InventoryUtil(Player player, Config config) {
        this.player = player;
        this.config = config;
    }

    public InventoryUtil(Config config) {
        this.config = config;
    }


    public void saveInventory(String section, @NotNull Inventory inv) {

        List<ItemStack> itemStacks = new ArrayList<>();
        ConfigurationSection configurationSection = this.config.getConfig().createSection(section + ".items");

        if (inv.isEmpty()) {
            configurationSection.set("items", null);
            this.config.saveConfig();
            return;
        }

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);

            if (item != null) {
                itemStacks.add(item);
                configurationSection.set(String.valueOf(i), item);
                this.config.saveConfig();
            }
        }
    }


    /**
     * get the inventory from the config
     * @param inventoryTitle inventory title
     * @param size inventory size * 9 [1~6]
     * @param section ConfigurationSection path
     */
    public void getInventory(String section, String inventoryTitle, Integer size, Player player) {
        Inventory inv = Bukkit.createInventory(null, size * 9, inventoryTitle);

        ConfigurationSection configurationSection = this.config.getConfig().getConfigurationSection(section + ".items");

        if (section != null) {
            for (String key : configurationSection.getKeys(false)) {
                inv.setItem(Integer.parseInt(key), configurationSection.getItemStack(key));
            }
        }
        this.player.openInventory(inv);
    }


    /**
     * get the inventory from the config
     * @param section ConfigurationSection path
     * @param inventoryTitle inventory title
     * @param size inventory size * 9 [1~6]
     */
    public void getInventory(String section, String inventoryTitle, Integer size) {
        Inventory inv = Bukkit.createInventory(null, size * 9, inventoryTitle);

        ConfigurationSection configurationSection = this.config.getConfig().getConfigurationSection(section + ".items");

        if (section != null) {
            for (String key : configurationSection.getKeys(false)) {
                inv.setItem(Integer.parseInt(key), configurationSection.getItemStack(key));
            }
        }
    }


    /**
     * get the inventory item
     * @param section ConfigurationSection path
     */
    public void getInventoryItem(String section) {
        ConfigurationSection configurationSection = this.config.getConfig().getConfigurationSection(section + ".items");

        for (String key : configurationSection.getKeys(false)) {
            player.getInventory().addItem(configurationSection.getItemStack(key));
        }
    }


    /**
     * is the inventory Full
     * @param section ConfigurationSection path
     * @return boolean
     */
    public Boolean isInventoryFull(String section, Integer size) {
        ConfigurationSection configurationSection = this.config.getConfig().getConfigurationSection(section + ".items");

        if(!(configurationSection.getKeys(false).size() > size * 9 - Arrays.stream(player.getInventory().getStorageContents()).filter(Objects::nonNull).count())) {
            return false;
        }
        return true;
    }
}

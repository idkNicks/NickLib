package com.github.nicklib.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtil {



    /**
     * Set the Item Stack
     * @param material Material
     * @param customModelData CustomModelData
     * @param name DisplayName
     * @param lore Lore
     */
    public ItemStack setItemStack(Material material, Integer customModelData, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(customModelData);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }


    /**
     * Set the Item Stack
     * @param material Material
     * @param name DisplayName
     * @param lore Lore
     */
    public ItemStack setItemStack(Material material, String name, List<String> lore) {

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}

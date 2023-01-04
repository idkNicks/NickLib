package com.github.nicklib.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Translate {

    public static List<String> color(List<String> arrays) {
        List<String> newArray = new ArrayList<>();

        for(String array : arrays) {

            array = ChatColor.translateAlternateColorCodes('&', array);
            newArray.add(array);
        }
        return newArray;
    }
}

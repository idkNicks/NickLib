package com.github.nicks.nicklib.data.location;


import com.github.nicks.nicklib.data.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Region {

    private HashMap<Player, Region> region = new HashMap<>();
    private String name;
    private Location pos1;
    private Location pos2;
    private Config config;


    public Region() {}


    public Region(String name) {
        this.name = name;
    }

    public Region(String name, Location pos1, Location pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public void saveRegion(String path) {
        config = new Config("region/" + name);
        config.setString(path + ".world", getPos1().getWorld().getName());

        config.setInt(path + ".pos1.x", getPos1().getBlockX());
        config.setInt(path + ".pos1.y", getPos1().getBlockY());
        config.setInt(path + ".pos1.z", getPos1().getBlockZ());

        config.setInt(path + ".pos2.x", getPos2().getBlockX());
        config.setInt(path + ".pos2.y", getPos2().getBlockY());
        config.setInt(path + ".pos2.z", getPos2().getBlockZ());
    }


    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }
}

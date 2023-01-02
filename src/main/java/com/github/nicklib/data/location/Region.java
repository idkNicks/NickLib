package com.github.nicklib.data.location;


import com.github.nicklib.data.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

@SuppressWarnings("all")
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


    public void saveRegion(String path, Location pos1, Location pos2) {
        config = new Config("region/" + path, null);
        config.setString(path + ".world", pos2.getWorld().getName());

        config.setInt(path + ".min.x", pos1.getBlockX());
        config.setInt(path + ".min.y", pos1.getBlockY());
        config.setInt(path + ".min.z", pos1.getBlockZ());

        config.setInt(path + ".max.x", pos2.getBlockX());
        config.setInt(path + ".max.y", pos2.getBlockY());
        config.setInt(path + ".max.z", pos2.getBlockZ());
        config.saveConfig();
    }


    public int squareSize(Vector pos1, Vector pos2) {
        Vector min = Vector.getMinimum(pos1, pos2);
        Vector max = Vector.getMaximum(pos1, pos2);
        Vector res = max.subtract(min);

        res.setX(Math.abs(res.getX()));
        res.setY(Math.abs(res.getY()));
        res.setZ(Math.abs(res.getZ()));
        res.add(new Vector(1, 1, 1));
        return res.getBlockX() * res.getBlockY() * res.getBlockZ();
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

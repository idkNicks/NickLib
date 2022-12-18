package com.github.nicks.nicklib.data;

import com.github.nicks.nicklib.data.impl.VariableConfigImpl;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("all")
public class Config implements VariableConfigImpl {

    private FileConfiguration config;
    private File file;
    private ConfigurationSection section;
    private final String name;
    private final JavaPlugin plugin;




    /**
     * Creates a new Config object
     */
    public Config(String name) {
        this.name = name;
        this.plugin = JavaPlugin.getProvidingPlugin(getClass());
    }


    /**
     * Gets the specified config
     */
    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return this.config;
    }


    /**
     * Save the config
     */
    public void saveConfig() {
        if (this.config == null || this.file == null) return;
        try {
            getConfig().save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reloads the specified config
     */
    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(plugin.getDataFolder(), name + ".yml");
            this.config = YamlConfiguration.loadConfiguration(this.file);

            InputStream inputStream = plugin.getResource(name + ".yml");
            if (inputStream != null) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
                this.config.setDefaults(config);
            }
        }
    }


    /**
     * Load the default Config
     */
    public void loadDefaultConfig() {
        File file = new File(plugin.getDataFolder(), this.name + ".yml");
        if (!file.exists()) {
            plugin.saveResource(this.name + ".yml", false);
        }
    }



    /**
     * Creates a new Directory
     */
    public void createDirectory(String name) {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * delete the config
     */
    public boolean delete() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file.delete();
    }


    /**
     * deletes a directory
     */
    public void deleteDirectory(String path) {
        File deleteFolder = new File(this.plugin.getDataFolder(), path);
        if (deleteFolder.exists()) {
            File[] deleteFolderList = deleteFolder.listFiles();

            for(int i = 0; i < deleteFolderList.length; ++i) {
                if (deleteFolderList[i].isFile()) {
                    deleteFolderList[i].delete();
                } else {
                    System.out.println(deleteFolderList[i].getPath());
                    this.deleteDirectory(deleteFolderList[i].getPath());
                }
                deleteFolderList[i].delete();
            }
            deleteFolder.delete();
        }
    }


    /**
     * Gets the FileList Name
     */
    public List<String> fileListName() {
        this.file = new File(plugin.getDataFolder(), name);
        ArrayList<String> newArray = new ArrayList<>();
        File[] list = this.file.listFiles();
        for (File file : list) {
            if (file != null) {
                String name = file.getName();
                name = name.replaceAll(".yml", "");
                newArray.add(name);
            }
        }
        return newArray;
    }


    /**
     * Check if config file exists
     */
    public boolean isFileExist() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file.exists();
    }


    /**
     * Gets the FileList
     */
    public File[] getFileList() {
        this.file = new File(plugin.getDataFolder(), name);
        return this.file.listFiles();
    }


    /**
     * Specifies the location.
     */
    public void setLocation(String path, Location location) {
        getConfig().set(path + ".world", location.getWorld().getName());
        getConfig().set(path + ".x", location.getX());
        getConfig().set(path + ".y", location.getY());
        getConfig().set(path + ".z", location.getZ());
        getConfig().set(path + ".yaw", location.getYaw());
        getConfig().set(path + ".pitch", location.getPitch());
        saveConfig();
    }


    public void setPos1(String path, Location location) {
        getConfig().set(path + "." + location.getWorld().getName() + ".min.x", location.getX());
        getConfig().set(path + "." + location.getWorld().getName() + ".min.y", location.getY());
        getConfig().set(path + "." + location.getWorld().getName() + ".min.z", location.getZ());
        saveConfig();
    }


    public void setPos2(String path, Location location) {
        getConfig().set(path + "." + location.getWorld().getName() + ".max.x", location.getX());
        getConfig().set(path + "." + location.getWorld().getName() + ".max.y", location.getY());
        getConfig().set(path + "." + location.getWorld().getName() + ".max.z", location.getZ());
        saveConfig();
    }



    @Override
    public void setString(String path, String value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setBoolean(String path, boolean value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setChar(String path, char value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setByte(String path, byte value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setShort(String path, short value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setInt(String path, int value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setLong(String path, long value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setFloat(String path, float value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setDouble(String path, double value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setStringList(String path, String[] value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void Object(String path, Object value) {
        getConfig().set(path, value);
        saveConfig();
    }


    @Override
    public String getString(String path) {
        return this.getConfig().getString(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return this.getConfig().getBoolean(path);
    }

    @Override
    public char getChar(String path) {
        return this.getConfig().getString(path).charAt(0);
    }

    @Override
    public byte getByte(String path) {
        return (byte) this.getConfig().getInt(path);
    }

    @Override
    public short getShort(String path) {
        return (short) this.getConfig().getInt(path);
    }

    @Override
    public int getInt(String path) {
        return this.getConfig().getInt(path);
    }

    @Override
    public long getLong(String path) {
        return this.getConfig().getLong(path);
    }

    @Override
    public float getFloat(String path) {
        return (float) this.getConfig().getDouble(path);
    }

    @Override
    public double getDouble(String path) {
        return this.getConfig().getDouble(path);
    }

    @Override
    public String[] getStringList(String path) {
        return this.getConfig().getStringList(path).toArray(new String[0]);
    }

    @Override
    public Object getObject(String path) {
        return this.getConfig().get(path);
    }
}

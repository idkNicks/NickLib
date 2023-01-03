package com.github.nicklib.data;

import com.github.nicklib.data.impl.VariableConfigImpl;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

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
     *
     * @param name   The name of the config file
     *               (without the .yml extension)
     *               (if the file is in a folder, use / to separate the folders)
     *               (example: "config" or "folder/config")
     *               (example: "config.yml" or "folder/config.yml" will not work)
     *               (example: "folder/config" or "folder/folder/config" will work)
     * @param plugin The plugin that the config is in
     */
    public Config(String name, JavaPlugin plugin) {
        this.name = name;
        this.plugin = plugin;
    }


    /**
     * Configuration setSection
     *
     * @param section The section of the config
     */
    public void setSection(ConfigurationSection section) {
        this.section = section;
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
    public boolean deleteFile() {
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

            for (int i = 0; i < deleteFolderList.length; ++i) {
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
     * Config file rename
     *
     * @param name The name of the config file
     */
    public void renameFile(String name) {
        this.file = new File(plugin.getDataFolder(), this.name + ".yml");
        File file = new File(plugin.getDataFolder(), name + ".yml");

        this.file.renameTo(file);
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
     * Copy the config file
     * @param newPath The path of the new file
     * @return The new file
     */
    public Boolean copyFile(String newPath) {
        if(this.file == null || this.config == null) return false;

        File newFile = new File(plugin.getDataFolder(), newPath);

        FileUtil.copy(file, newFile);
        plugin.saveResource(newFile.getPath(), false);

        return FileUtil.copy(file, newFile);
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
     * Gets the ConfigurationSection
     *
     * @param path
     * @return ConfigurationSection
     */
    public ConfigurationSection getConfigurationSection(String path) {
        return getConfig().getConfigurationSection(path);
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
    public void setObject(String path, Object value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setObjectList(String path, List<Object> value) {
        getConfig().set(path, value);
        saveConfig();
    }

    @Override
    public void setStringList(String path, List<String> value) {
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
    public Object getObject(String path) {
        return this.getConfig().get(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return this.getConfig().getStringList(path);
    }

    @Override
    public List<Object> getObjectList(String path) {
        return (List<Object>) this.getConfig().getList(path);
    }
}

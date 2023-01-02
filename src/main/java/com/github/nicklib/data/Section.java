package com.github.nicklib.data;

import com.github.nicklib.data.impl.VariableConfigImpl;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class Section implements VariableConfigImpl {


    private ConfigurationSection section;
    private Config config;


    public Section(Config config, String path) {
        this.config = config;
    }


    /**
     * add Arrays value to the config
     * @param path    the path of the value
     * @param newPath the new path of the new value
     * @return the new value
     */
    public ConfigurationSection addArray(String path, String newPath) {
        this.section = config.getConfig().getConfigurationSection(path + "." + config.getConfig().getConfigurationSection(path).getKeys(false).size());

        if (config.getConfig().getConfigurationSection(path) != null) {
            if (section != null) {
                return section;
            }
        } else {
            return config.getConfig().createSection(section.getCurrentPath() + "." + newPath);
        }
        return null;
    }


    @Override
    public void setString(String path, String value) {
        section.set(path, value);
    }

    @Override
    public void setBoolean(String path, boolean value) {
        section.set(path, value);
    }

    @Override
    public void setChar(String path, char value) {
        section.set(path, value);
    }

    @Override
    public void setByte(String path, byte value) {
        section.set(path, value);
    }

    @Override
    public void setShort(String path, short value) {
        section.set(path, value);
    }

    @Override
    public void setInt(String path, int value) {
        section.set(path, value);
    }

    @Override
    public void setLong(String path, long value) {
        section.set(path, value);
    }

    @Override
    public void setFloat(String path, float value) {
        section.set(path, value);
    }

    @Override
    public void setDouble(String path, double value) {
        section.set(path, value);
    }

    @Override
    public void setObject(String path, Object value) {
        section.set(path, value);
    }

    @Override
    public void setObjectList(String path, List<Object> value) {
        section.set(path, value);
    }

    @Override
    public void setStringList(String path, List<String> value) {
        section.set(path, value);
    }

    @Override
    public String getString(String path) {
        return section.getString(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return section.getBoolean(path);
    }

    @Override
    public char getChar(String path) {
        return section.getString(path).charAt(0);
    }

    @Override
    public byte getByte(String path) {
        return (byte) section.getInt(path);
    }

    @Override
    public short getShort(String path) {
        return (short) section.getInt(path);
    }

    @Override
    public int getInt(String path) {
        return section.getInt(path);
    }

    @Override
    public long getLong(String path) {
        return section.getLong(path);
    }

    @Override
    public float getFloat(String path) {
        return (float) section.getDouble(path);
    }

    @Override
    public double getDouble(String path) {
        return section.getDouble(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return section.getStringList(path);
    }

    @Override
    public List<Object> getObjectList(String path) {
        return null;
    }

    @Override
    public Object getObject(String path) {
        return section.get(path);
    }
}

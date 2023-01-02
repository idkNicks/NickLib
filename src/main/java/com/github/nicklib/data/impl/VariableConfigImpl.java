package com.github.nicklib.data.impl;

import java.util.List;

@SuppressWarnings("all")
public interface VariableConfigImpl {

    void setString(String path, String value);

    void setBoolean(String path, boolean value);

    void setChar(String path, char value);

    void setByte(String path, byte value);

    void setShort(String path, short value);

    void setInt(String path, int value);

    void setLong(String path, long value);

    void setFloat(String path, float value);

    void setDouble(String path, double value);

    void setObject(String path, Object value);

    void setObjectList(String path, List<Object> value);

    void setStringList(String path, List<String> value);


    String getString(String path);

    boolean getBoolean(String path);

    char getChar(String path);

    byte getByte(String path);

    short getShort(String path);

    int getInt(String path);

    long getLong(String path);

    float getFloat(String path);

    double getDouble(String path);

    Object getObject(String path);

    List<String> getStringList(String path);

    List<Object> getObjectList(String path);
}

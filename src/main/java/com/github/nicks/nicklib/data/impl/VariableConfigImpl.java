package com.github.nicks.nicklib.data.impl;

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

    void setStringList(String path, String[] value);

    void Object(String path, Object value);



    String getString(String path);

    boolean getBoolean(String path);

    char getChar(String path);

    byte getByte(String path);

    short getShort(String path);

    int getInt(String path);

    long getLong(String path);

    float getFloat(String path);

    double getDouble(String path);

    String[] getStringList(String path);

    Object getObject(String path);

}

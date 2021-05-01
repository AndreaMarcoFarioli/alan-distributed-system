package example;

public interface DataTable {
    int getInt(String key);
    double getDouble(String key);
    float getFloat(String key);
    String getString(String key);
    long getLong(String key);
    Object getObject(String key);
    void setInt(String key, int value);
    void setDouble(String key, double value);
    void setFloat(String key, float value);
    void setString(String key, String value);
    void setLong(String key, long value);
    void setObject(String key, Object value);
}

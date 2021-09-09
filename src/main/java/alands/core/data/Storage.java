package alands.core.data;

public interface Storage {
    <T>void set(String name, T value);
    <T> T get(String name, Class<T> type);
    void clear(String name);
}

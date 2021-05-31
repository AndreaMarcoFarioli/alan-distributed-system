package alands.core.data;

public interface Storage {
    <T>void setParameter(String name, T value);
    <T> T getParameter(String name, Class<T> type);
    void clear(String name);
}

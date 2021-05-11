package bombe2.core.data;

public interface Storage {
    <T>void setParameter(String name, T value);
    Object getParameter(String name, Class<?> type);
    void clear(String name);
}

package alands.core.data;

import java.util.HashMap;
import java.util.Map;

public class LocalStorage implements Storage{
    private final Map<String, Object> parameters = new HashMap<>();

    @Override
    public <T> void setParameter(String name, T value) {
        parameters.put(name, value);
    }

    @Override
    public <T>T getParameter(String name, Class<T> type) {
        return type.cast(parameters.get(name));
    }

    @Override
    public void clear(String name) {
        parameters.remove(name);
    }
}

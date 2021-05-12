package bombe2.core.data;

import java.util.HashMap;
import java.util.Map;

public class LocalStorage implements Storage{
    private final Map<String, Object> parameters = new HashMap<>();

    @Override
    public <T> void setParameter(String name, T value) {
        parameters.put(name, value);
    }

    @Override
    public Object getParameter(String name, Class<?> type) {
        return parameters.get(name);
    }

    @Override
    public void clear(String name) {
        parameters.remove(name);
    }
}

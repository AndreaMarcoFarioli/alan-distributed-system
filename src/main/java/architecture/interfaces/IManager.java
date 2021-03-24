package architecture.interfaces;

import architecture.Service;

import java.util.function.Consumer;

public interface IManager {
    <T>void iterateAll(Consumer<T> consumer);
    Service getService(String key);
    void addService(Service service);
    void destroyService(Service service);
}

package bombe2.core.definitions;



import bombe2.core.AbstractService;

@Deprecated
public interface IManager {
    void addService(AbstractService service);
    void deleteService(AbstractService service);
    AbstractService getService(String name);
}

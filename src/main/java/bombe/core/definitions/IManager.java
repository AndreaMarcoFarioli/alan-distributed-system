package bombe.core.definitions;


import bombe.core.AbstractService;
import bombe.core.ExtendableService;
import bombe.core.Service;

public interface IManager {
    AbstractService addService(Class<? extends AbstractService> service);
    void deleteService(AbstractService service);
    void deleteServiceByName(String name);
    AbstractService getService(String name);
}

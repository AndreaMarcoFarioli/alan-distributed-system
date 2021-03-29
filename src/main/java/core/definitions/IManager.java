package core.definitions;

import core.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

public interface IManager {
    void addService(Service service) throws KeyAlreadyExistsException;
    void deleteService(Service service) throws NoSuchElementException;
    Service getService(String name) throws NoSuchElementException;
}

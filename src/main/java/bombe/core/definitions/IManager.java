package bombe.core.definitions;



import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

public interface IManager {
    void addService(AbstractService service) throws KeyAlreadyExistsException;
    void deleteService(AbstractService service) throws NoSuchElementException;
    AbstractService getService(String name) throws NoSuchElementException;
}

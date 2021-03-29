package core.definitions;

import core.EventObject;
import core.Manager;
import core.ReturnableObject;
import core.Service;
import distributedArchitecture.HasManager;
import exceptions.PropagationException;

import java.lang.reflect.InvocationTargetException;

public abstract class ExtendableService extends Service implements HasManager {
    private final Manager manager = new Manager();

    public ExtendableService(String name, ServiceModel methods) {
        super(name, methods);
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public ReturnableObject<?> propagate(EventObject eventObject) throws Exception {
        ReturnableObject<?> returnableObject;
        if (eventObject.hasNext())
            returnableObject = manager.propagate(eventObject);
        else
            returnableObject = super.propagate(eventObject);
        return returnableObject;
    }
}

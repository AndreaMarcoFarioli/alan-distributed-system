package distributedArchitecture;

import core.EventObject;
import core.Manager;
import core.ReturnableObject;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class MainManager extends UnicastRemoteObject implements RemoteNode, HasManager {
    private final Manager manager = new Manager();

    public MainManager() throws RemoteException {
    }

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws Exception {
        ReturnableObject<?> returnableObject;
        returnableObject = manager.propagate(eventObject);
        return returnableObject;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
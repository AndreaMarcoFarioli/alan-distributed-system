package alands.distributed;

import alands.core.data.EventObject;
import alands.core.Manager;
import alands.core.data.ReturnableObject;
import alands.core.definitions.HasManager;
import alands.core.definitions.Propagator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class RootManager implements Propagator, HasManager {
    private final Manager manager = new Manager();
    private InterComChannel interComChannel;

    private static RootManager instance = null;

    private RootManager() {
    }

    public static RootManager getInstance(){
        if (instance == null)
            instance = new RootManager();
        return instance;
    }

    public void init(InterComChannel interComUnit){
        if (this.interComChannel == null)
            this.interComChannel = interComUnit;
    }

    public InterComChannel getInterComChannel() {
        return interComChannel;
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public ReturnableObject<?> propagate(EventObject eventObject) throws ReflectiveOperationException {
        return getManager().propagate(eventObject);
    }
}
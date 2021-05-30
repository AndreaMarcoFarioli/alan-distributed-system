package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.core.Manager;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.HasManager;
import bombe2.core.definitions.Propagator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class RootManager extends UnicastRemoteObject implements Propagator, HasManager {
    private final Manager manager = new Manager();
    private InterComChannel interComChannel;

    private static RootManager instance = null;

    private RootManager() throws RemoteException {
    }

    public static RootManager getInstance(){
        if (instance == null) {
            try {
                instance = new RootManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
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
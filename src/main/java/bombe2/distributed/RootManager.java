package bombe2.distributed;

import bombe2.annotations.Origin;
import bombe2.core.data.EventObject;
import bombe2.core.Manager;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.HasManager;
import bombe2.core.definitions.Propagator;
import bombe2.exceptions.MalformedEventException;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class RootManager implements HasManager, Propagator {
    private final Manager manager = new Manager();
    private InterComUnit interComUnit;
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

    public void init(InterComUnit interComUnit){
        if (this.interComUnit == null)
            this.interComUnit = interComUnit;
    }

    /**
     *
     * @param eventObject
     * @return
     * @throws Exception
     */
    @Override
    public ReturnableObject<?> propagate(EventObject eventObject) throws ReflectiveOperationException {
        ReturnableObject<?> returnableObject;
        Field field = EventObject.class.getDeclaredField("origin");
        field.setAccessible(true);
        field.set(eventObject, Origin.REMOTE);
        field.setAccessible(false);
        returnableObject = manager.propagate(eventObject);
        return returnableObject;
    }

    public ReturnableObject<?> sendOver(EventObject eventObject)
            throws ReflectiveOperationException, RemoteException, MalformedEventException {
        return interComUnit.sendOver(eventObject);
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
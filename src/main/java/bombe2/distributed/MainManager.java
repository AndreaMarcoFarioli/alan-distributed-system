package bombe2.distributed;

import bombe2.annotations.Origin;
import bombe2.core.data.EventObject;
import bombe2.core.Manager;
import bombe2.core.data.ReturnableObject;
import bombe2.exceptions.MalformedEventException;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class MainManager extends UnicastRemoteObject implements RemoteNode, HasManager {
    private final Manager manager = new Manager();
    private NodeProvider nodeProvider;
    private static MainManager instance = null;

    private MainManager() throws RemoteException {
    }

    public static MainManager getInstance(){
        if (instance == null) {
            try {
                instance = new MainManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     *
     * @param eventObject
     * @return
     * @throws Exception
     */
    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException {
        ReturnableObject<?> returnableObject;
        Field field = EventObject.class.getDeclaredField("origin");
        field.setAccessible(true);
        field.set(eventObject, Origin.REMOTE);
        field.setAccessible(false);
        returnableObject = manager.propagate(eventObject);
        return returnableObject;
    }

    public void setNodeProvider(NodeProvider nodeProvider) {
        this.nodeProvider = nodeProvider;
    }

    public ReturnableObject<?> sendOver(EventObject eventObject)
            throws ReflectiveOperationException, RemoteException, MalformedEventException {
        ReturnableObject<?> returnableObject = null;
        returnableObject = nodeProvider.getNode().call(nodeProvider.middleware(eventObject));
        return returnableObject;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
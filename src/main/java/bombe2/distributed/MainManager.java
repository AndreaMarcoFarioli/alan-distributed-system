package bombe2.distributed;

import bombe2.annotations.Origin;
import bombe2.core.data.EventObject;
import bombe2.core.Manager;
import bombe2.core.data.ReturnableObject;

import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class MainManager extends UnicastRemoteObject implements RemoteNode, HasManager {
    private final Manager manager = new Manager();
    private NodeProvider nodeProvider;
    private final ClusterTiming clusterTiming = new ClusterTiming();
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

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws Exception {
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

    public ReturnableObject<?> sendOver(EventObject eventObject) throws Exception{
        return nodeProvider.getNode().call(nodeProvider.middleware(eventObject));
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    public ClusterTiming getClusterTiming() {
        return clusterTiming;
    }
}
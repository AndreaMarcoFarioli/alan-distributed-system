package bombe.distributedArchitecture;

import bombe.core.data.ClusterEnvironment;
import bombe.core.data.EventObject;
import bombe.core.Manager;
import bombe.core.data.ReturnableObject;
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

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws Exception {
        ReturnableObject<?> returnableObject;
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
}
package bombe2.distributedArchitecture.defaultServices.master;

import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.distributedArchitecture.NodeProvider;
import bombe2.distributedArchitecture.RemoteNode;

import java.rmi.RemoteException;

public class NodeManager extends Service implements NodeProvider {

    private NodeManager() {
        super("NodeManager", new NodeManagerModel());
    }

    @Override
    public RemoteNode getNode() throws RemoteException {
        return null;
    }

    @Override
    public EventObject middleware(EventObject eventObject) {
        return null;
    }

    @Override
    public void beforeInvocation(Object param) {

    }

    @Override
    public void afterInvocation(Object param) {

    }

}

package bombe2.distributed.std_servicies.master;

import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.distributed.NodeProvider;
import bombe2.distributed.RemoteNode;

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

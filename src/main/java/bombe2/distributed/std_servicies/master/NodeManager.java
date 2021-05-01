package bombe2.distributed.std_servicies.master;

import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.distributed.NodeProvider;
import bombe2.distributed.RemoteNode;

import java.rmi.RemoteException;

public abstract class NodeManager extends Service implements NodeProvider {

    private NodeManager() {
        super("NodeManager");
    }

    @Override
    public abstract RemoteNode getNode() throws RemoteException;
    @Override
    public abstract EventObject middleware(EventObject eventObject);

}

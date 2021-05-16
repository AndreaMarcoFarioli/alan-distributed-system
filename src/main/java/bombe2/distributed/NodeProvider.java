package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.exceptions.MalformedEventException;

import java.rmi.RemoteException;


public abstract class NodeProvider {

    public abstract RemoteNode getNode() throws RemoteException;
    @Deprecated
    public abstract EventObject middleware(EventObject eventObject) throws MalformedEventException;

    public ReturnableObject<?> call(EventObject eventObject)
            throws RemoteException, MalformedEventException, ReflectiveOperationException {
        return getNode().call(middleware(eventObject));
    }
}
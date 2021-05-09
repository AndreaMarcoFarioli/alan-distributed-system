package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.exceptions.MalformedEventException;

import java.rmi.RemoteException;


public interface NodeProvider {
    RemoteNode getNode() throws RemoteException;
    EventObject middleware(EventObject eventObject) throws MalformedEventException;
}

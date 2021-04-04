package bombe.distributedArchitecture;

import bombe.core.data.EventObject;

import java.rmi.RemoteException;

public interface NodeProvider extends TransactionsManager {
    RemoteNode getNode() throws RemoteException;
    EventObject middleware(EventObject eventObject);
}

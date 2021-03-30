package bombe.distributedArchitecture;

import java.rmi.RemoteException;

public interface NodeProvider extends TransactionsManager {
    RemoteNode getNode() throws RemoteException;
}

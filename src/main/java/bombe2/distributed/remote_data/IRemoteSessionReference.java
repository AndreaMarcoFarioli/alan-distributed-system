package bombe2.distributed.remote_data;

import bombe2.core.SessionReference;
import bombe2.core.data.Storage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteSessionReference extends Remote {
    void destroy() throws RemoteException;
    String getSessionId()throws RemoteException;
    boolean isOpened()throws RemoteException;
    IRemoteStorage getStorage()throws RemoteException;
}

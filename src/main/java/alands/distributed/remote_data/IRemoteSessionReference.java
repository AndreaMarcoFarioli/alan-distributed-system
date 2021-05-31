package alands.distributed.remote_data;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteSessionReference extends Remote {
    void destroy() throws RemoteException;
    String getSessionId()throws RemoteException;
    boolean isOpened()throws RemoteException;
    IRemoteStorage getStorage()throws RemoteException;
}

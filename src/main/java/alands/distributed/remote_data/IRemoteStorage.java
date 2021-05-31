package alands.distributed.remote_data;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteStorage extends Remote {
    <T>void setParameter(String name, T value) throws RemoteException;
    <T> T getParameter(String name, Class<T> type) throws RemoteException;
    void clear(String name) throws RemoteException;
}

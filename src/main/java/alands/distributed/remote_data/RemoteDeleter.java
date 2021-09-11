package alands.distributed.remote_data;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteDeleter<T> extends Remote {
    boolean delete(T object) throws RemoteException;
}

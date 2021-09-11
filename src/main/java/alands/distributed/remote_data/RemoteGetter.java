package alands.distributed.remote_data;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGetter<T> extends Remote {
    T get() throws RemoteException;
}

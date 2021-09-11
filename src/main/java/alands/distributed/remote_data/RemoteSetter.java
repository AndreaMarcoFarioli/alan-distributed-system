package alands.distributed.remote_data;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteSetter<T> extends Remote {
    void set(T value) throws RemoteException, NotBoundException;
}

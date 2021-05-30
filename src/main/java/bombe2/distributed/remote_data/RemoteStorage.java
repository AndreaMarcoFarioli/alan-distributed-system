package bombe2.distributed.remote_data;

import bombe2.core.data.Storage;
import bombe2.distributed.database_implementation.DatabaseStorage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteStorage extends UnicastRemoteObject implements IRemoteStorage {
    private final Storage storage;
    public RemoteStorage(Storage storage) throws RemoteException {
        this.storage = storage;
    }

    @Override
    public <T> void setParameter(String name, T value) throws RemoteException {
        storage.setParameter(name, value);
    }

    @Override
    public <T> T getParameter(String name, Class<T> type) throws RemoteException {
        return storage.getParameter(name, type);
    }

    @Override
    public void clear(String name) throws RemoteException {
        storage.clear(name);
    }
}

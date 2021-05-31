package alands.distributed.remote_data;

import alands.core.data.Storage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class RemoteStorage extends UnicastRemoteObject implements IRemoteStorage {
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

package alands.distributed.remote_data;

import alands.core.data.Storage;
import alands.exceptions.SessionException;

import java.rmi.RemoteException;

public final class ReferencedStorage implements Storage {
    private final IRemoteStorage remoteStorage;
    public ReferencedStorage(IRemoteStorage remoteStorage){
        this.remoteStorage = remoteStorage;
    }

    @Override
    public <T> void set(String name, T value) {
        try {
            remoteStorage.set(name, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T get(String name, Class<T> type) {
        try {
            return remoteStorage.get(name, type);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }

    @Override
    public void clear(String name) {
        try {
            remoteStorage.clear(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

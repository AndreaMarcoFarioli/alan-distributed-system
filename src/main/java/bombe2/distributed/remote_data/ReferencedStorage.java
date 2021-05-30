package bombe2.distributed.remote_data;

import bombe2.core.data.Storage;
import bombe2.exceptions.SessionException;

import java.rmi.RemoteException;

public class ReferencedStorage implements Storage {
    private final IRemoteStorage remoteStorage;
    public ReferencedStorage(IRemoteStorage remoteStorage){
        this.remoteStorage = remoteStorage;
    }

    @Override
    public <T> void setParameter(String name, T value) {
        try {
            remoteStorage.setParameter(name, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getParameter(String name, Class<T> type) {
        try {
            return remoteStorage.getParameter(name, type);
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

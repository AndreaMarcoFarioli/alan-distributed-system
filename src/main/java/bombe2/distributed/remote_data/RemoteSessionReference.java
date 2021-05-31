package bombe2.distributed.remote_data;

import bombe2.core.SessionReference;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public final class RemoteSessionReference extends UnicastRemoteObject implements IRemoteSessionReference {
    private final SessionReference sessionReference;
    public RemoteSessionReference(SessionReference sessionReference) throws RemoteException {
        this.sessionReference = sessionReference;
    }


    @Override
    public void destroy() throws RemoteException {
        sessionReference.destroy();
    }

    @Override
    public String getSessionId() throws RemoteException{
        return sessionReference.getSessionId();
    }

    @Override
    public boolean isOpened()  throws RemoteException{
        return sessionReference.isOpened();
    }

    @Override
    public IRemoteStorage getStorage() throws RemoteException {
        return new RemoteStorage(sessionReference.getStorage());
    }
}

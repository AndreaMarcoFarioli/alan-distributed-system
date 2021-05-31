package bombe2.distributed.remote_data;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.Storage;
import bombe2.exceptions.SessionException;

import java.rmi.RemoteException;

public final class ReferencedSessionReference extends SessionReference {
    private final IRemoteSessionReference remoteSessionReference;
    protected ReferencedSessionReference(SessionManager sessionManager, IRemoteSessionReference remoteSessionReference) {
        super(sessionManager, null, null);
        this.remoteSessionReference = remoteSessionReference;
    }

    @Override
    public String getSessionId() {
        try {
            return remoteSessionReference.getSessionId();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }

    @Override
    public Storage getStorage() {
        try {
            return new ReferencedStorage(remoteSessionReference.getStorage());
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }

    @Override
    public boolean isOpened() {
        try {
            return remoteSessionReference.isOpened();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }
}

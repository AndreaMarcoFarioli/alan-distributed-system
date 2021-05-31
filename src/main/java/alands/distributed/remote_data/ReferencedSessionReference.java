package alands.distributed.remote_data;

import alands.core.SessionManager;
import alands.core.SessionReference;
import alands.core.data.Storage;
import alands.exceptions.SessionException;

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

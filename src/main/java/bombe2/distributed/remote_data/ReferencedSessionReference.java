package bombe2.distributed.remote_data;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.Storage;
import bombe2.exceptions.SessionException;

import java.rmi.RemoteException;

public class ReferencedSessionReference extends SessionReference {
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
            System.out.println();
            return new ReferencedStorage(remoteSessionReference.getStorage());
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }
}

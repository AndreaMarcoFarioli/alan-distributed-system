package alands.distributed.remote_data;

import alands.core.SessionManager;
import alands.core.SessionReference;
import alands.exceptions.SessionException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public final class ReferencedSessionManager extends SessionManager {
    private final String host, name;
    private final int port;
    private final IRemoteSessionManager remoteManager;
    public ReferencedSessionManager(String host, int port, String name){
        this.port = port;
        this.host = host;
        this.name = name;
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(this.host, this.port);
            remoteManager = (IRemoteSessionManager) registry.lookup(this.name);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new SessionException("Session Manager not created");
        }
    }

    @Override
    public SessionReference createSession() {
        try {
            return new ReferencedSessionReference(this, remoteManager.createSession());
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }

    @Override
    public SessionReference getSession(String sessionId) {
        try {
            return new ReferencedSessionReference(this, remoteManager.getSession(sessionId));
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new SessionException();
        }
    }

    @Override
    public void destroySession(String sessionId) {
        try {
            remoteManager.destroySession(sessionId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int sessionCount() {
        int count = -1;
        try {
            count = remoteManager.sessionCount();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void destroyAll() {
        try {
            remoteManager.destroyAll();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

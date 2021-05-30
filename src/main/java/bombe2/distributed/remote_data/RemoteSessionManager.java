package bombe2.distributed.remote_data;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.distributed.remote_data.IRemoteSessionManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteSessionManager extends UnicastRemoteObject implements IRemoteSessionManager {
    private final SessionManager sessionManager;

    public RemoteSessionManager(SessionManager sessionManager) throws RemoteException {
        super();
        this.sessionManager = sessionManager;
    }

    @Override
    public int sessionCount() throws RemoteException {
        return sessionManager.sessionCount();
    }

    @Override
    public void destroyAll() throws RemoteException {
        sessionManager.destroyAll();
    }

    @Override
    public void destroySession(String sessionId) throws RemoteException {
        sessionManager.destroySession(sessionId);
    }

    @Override
    public boolean available(String sessionId) throws RemoteException{
        return sessionManager.available(sessionId);
    }

    @Override
    public IRemoteSessionReference getSession(String sessionId) throws RemoteException {
        return new RemoteSessionReference(sessionManager.getSession(sessionId));
    }

    @Override
    public String generateSessionId() throws RemoteException {
        return sessionManager.generateSessionId();
    }

    @Override
    public IRemoteSessionReference createSession() throws RemoteException {
        return new RemoteSessionReference(sessionManager.createSession());
    }
}

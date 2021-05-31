import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.ISessionManager;
import bombe2.distributed.database_implementation.DatabaseSessionManager;
import bombe2.distributed.remote_data.RemoteSessionManager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class T1 {
    public static void main(String[] args) throws RemoteException {
        SessionManager sessionManager = new DatabaseSessionManager();
        RemoteSessionManager remoteSessionManager = new RemoteSessionManager(sessionManager);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("remoteSessionManager", remoteSessionManager);
    }
}

class Proxy extends SessionManager{
    private SessionManager sessionManager;

    public Proxy(SessionManager sessionManager){
        this.sessionManager = sessionManager;
    }
    @Override
    public int sessionCount() {
        System.out.println("sessionCount");
        return sessionManager.sessionCount();
    }

    @Override
    public void destroyAll() {
        sessionManager.destroyAll();
    }

    @Override
    public void destroySession(String sessionId) {
        sessionManager.destroySession(sessionId);
    }

    @Override
    public boolean available(String sessionId) {
        return sessionManager.available(sessionId);
    }

    @Override
    public SessionReference getSession(String sessionId) {
        return sessionManager.getSession(sessionId);
    }

    @Override
    public String generateSessionId() {
        return sessionManager.generateSessionId();
    }

    @Override
    public SessionReference createSession() {
        System.out.println("eseguo");
        return sessionManager.createSession();
    }
}
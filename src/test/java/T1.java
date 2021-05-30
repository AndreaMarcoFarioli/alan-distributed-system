import bombe2.core.SessionManager;
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

package alands.distributed.remote_data;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteSessionManager extends Remote {
    int sessionCount() throws RemoteException;
    void destroyAll() throws RemoteException;
    void destroySession(String sessionId)throws RemoteException;
    boolean available(String sessionId)throws RemoteException;
    IRemoteSessionReference getSession(String sessionId)throws RemoteException;
    String generateSessionId()throws RemoteException;
    IRemoteSessionReference createSession()throws RemoteException;
}

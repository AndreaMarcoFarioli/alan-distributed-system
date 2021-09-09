package alands.beta;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProcess extends Remote {
    void exit(int code) throws RemoteException;
    boolean isAlive() throws RemoteException;
}

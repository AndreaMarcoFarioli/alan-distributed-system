package alands.beta;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteProcess extends UnicastRemoteObject implements IProcess {
    protected RemoteProcess() throws RemoteException {
    }

    @Override
    public void exit(int code) throws RemoteException {

    }

    @Override
    public boolean isAlive() throws RemoteException {
        return false;
    }
}

package architecture;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InputRMI extends Remote {
    ReturnableObject call(EventObject eventObject) throws RemoteException;
}

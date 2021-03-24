package finals;

import architecture.EventObject;
import architecture.InputRMI;
import architecture.Manager;
import architecture.ReturnableObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MainManager extends UnicastRemoteObject implements InputRMI {
    private final Manager manager = new Manager("main");
    public MainManager() throws RemoteException {
    }

    @Override
    public ReturnableObject call(EventObject eventObject) throws RemoteException {
        System.out.println("prima");
        ReturnableObject returnableObject = manager.propagate(eventObject);
        System.out.println("dopo");
        return returnableObject;
    }
}

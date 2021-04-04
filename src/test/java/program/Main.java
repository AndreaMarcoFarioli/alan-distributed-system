package program;

import bombe.core.ExtendableService;
import bombe.core.Service;
import bombe.distributedArchitecture.MainManager;
import bombe.distributedArchitecture.defaultServices.Redirect;
import bombe.exceptions.MalformedEventException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) throws RemoteException, MalformedEventException {
        Service service = new Redirect();
        MainManager.getInstance().getManager().addService(service);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("main", MainManager.getInstance());
    }
}

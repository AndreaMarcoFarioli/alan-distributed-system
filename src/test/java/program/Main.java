package program;

import bombe.core.Service;
import bombe.core.data.EventObject;
import bombe.distributedArchitecture.MainManager;
import bombe.distributedArchitecture.defaultServices.master.Redirect;
import bombe.exceptions.MalformedEventException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) throws RemoteException, MalformedEventException {

        Service service = new Redirect();
        Service service1 = new ServizioB();

        MainManager.getInstance().getManager().addService(service);
        MainManager.getInstance().getManager().addService(service1);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("main", MainManager.getInstance());
    }
}

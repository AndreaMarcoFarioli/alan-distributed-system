package program;

import bombe.core.ExtendableService;
import bombe.distributedArchitecture.MainManager;
import bombe.exceptions.MalformedEventException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedEventException {
        ExtendableService extendableService = new ServizioA();
        extendableService.getManager().addService(new ServizioA());
        MainManager.getInstance().getManager().addService(extendableService);
        MainManager.getInstance().getManager().create();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("main", MainManager.getInstance());
    }
}

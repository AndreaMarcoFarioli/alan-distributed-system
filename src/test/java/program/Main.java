package program;

import core.EventObject;
import core.Manager;
import core.ReturnableObject;
import core.Service;
import core.definitions.ExtendableService;
import distributedArchitecture.MainManager;
import exceptions.MalformedEventException;
import exceptions.PropagationException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws RemoteException {
        MainManager mainManager = new MainManager();
        ExtendableService extendableService = new ServizioA();
        extendableService.getManager().addService(new ServizioA());
        mainManager.getManager().addService(extendableService);
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("main", mainManager);
    }
}

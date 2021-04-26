package program;

import bombe2.core.AbstractService;
import bombe2.core.ExtendableService;
import bombe2.core.Service;
import bombe2.distributedArchitecture.MainManager;
import bombe2.distributedArchitecture.defaultServices.master.Redirect;
import bombe2.exceptions.MalformedEventException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {

        AbstractService service = new Redirect();
        AbstractService service1 = new ServizioB();
        ExtendableService service2 = new ServizioA();
        service2.getManager().addService(service1);

        //MainManager.getInstance().getManager().addService(service);
        MainManager.getInstance().getManager().addService(service2);

        MainManager.getInstance().getManager().create();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("main", MainManager.getInstance());
    }
}

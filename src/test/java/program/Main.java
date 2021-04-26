package program;

import bombe2.core.AbstractService;
import bombe2.core.ExtendableService;
import bombe2.distributed.MainManager;
import bombe2.distributed.std_servicies.master.Redirect;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {

        AbstractService service = new Redirect();
        ExtendableService service2 = new ServizioA();

        //MainManager.getInstance().getManager().addService(service);
        MainManager.getInstance().getManager().addService(service2);

        MainManager.getInstance().getManager().create();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("main", MainManager.getInstance());
    }
}

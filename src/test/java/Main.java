import bombe2.core.Manager;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.*;
import bombe2.exceptions.MalformedEventException;
import bombe2.distributed.database_implementation.DatabaseComUnit;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static int port = 1000 + (int)(Math.random()*100);
    public static void main(String[] args) throws Exception {

        //SessionReference sessionReference = SystemSessionReference.getInstance();
        //sessionReference.getStorage().setParameter("port", port);

        Registry registry = LocateRegistry.createRegistry(port);

        DatabaseComUnit comUnit = new DatabaseComUnit(RootManager.getInstance(), port);

        registry.rebind("com", comUnit);

        RootManager.getInstance().init(comUnit);

        Manager manager = RootManager.getInstance().getManager();

        manager.addService(new MathService());

        comUnit.setNodeAvailability(true);


        ReturnableObject<?> r = RootManager.getInstance().sendOver(new EventObject("math:sum", 10));

        System.out.println(r);

    }
}
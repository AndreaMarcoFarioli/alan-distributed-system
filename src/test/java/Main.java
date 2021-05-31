import bombe2.core.Manager;
import bombe2.core.SessionReference;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.*;
import bombe2.distributed.database_implementation.DatabaseSessionManager;
import bombe2.exceptions.MalformedEventException;
import bombe2.distributed.database_implementation.DatabaseComUnit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static int port = 1000 + (int)(Math.random()*100);
    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.createRegistry(port);

        DatabaseComUnit comUnit = new DatabaseComUnit(RootManager.getInstance(), port);

        registry.rebind("com", comUnit);

        RootManager.getInstance().init(comUnit);

        Manager manager = RootManager.getInstance().getManager();

        manager.addService(new MathService());

        comUnit.setNodeAvailability(false);

        Thread.sleep(10000);

        comUnit.setNodeAvailability(true);

        DatabaseSessionManager databaseSessionManager = new DatabaseSessionManager();
        SessionReference sessionReference = databaseSessionManager.createSession();
        sessionReference.getStorage().setParameter("var1", "ciao");
    }
}

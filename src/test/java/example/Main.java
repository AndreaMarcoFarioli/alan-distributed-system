package example;

import bombe2.core.AbstractService;
import bombe2.distributed.SessionProvider;
import bombe2.core.Manager;
import bombe2.distributed.RootManager;
import bombe2.exceptions.MalformedEventException;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws MalformedEventException, ReflectiveOperationException, RemoteException {
        Manager rootManager = RootManager.getInstance().getManager();
        AbstractService service = new ExampleService();
        rootManager.addService(service);
        SessionProvider sessionProvider = new SessionProvider();
        rootManager.propagate(sessionProvider.generateEventObject("exampleService:method"));
        sessionProvider.destroy();
        sessionProvider = null;
        System.gc();
    }
}

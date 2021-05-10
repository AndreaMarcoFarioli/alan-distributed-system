package example;

import bombe2.alpha.SessionReference;
import bombe2.core.Manager;
import bombe2.core.data.EventObject;
import bombe2.distributed.RootManager;
import bombe2.exceptions.MalformedEventException;
import bombe2.exceptions.PropagationException;
import test_services.ServiceTest;

import java.lang.reflect.InvocationTargetException;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws MalformedEventException, ReflectiveOperationException, RemoteException {
        Manager manager = RootManager.getInstance().getManager();
        manager.addService(new ServiceTest());
        manager.create();
        manager.start();
        SessionReference sessionReference = EventObject.createSession();
        try {
            RootManager.getInstance().call(new EventObject("test:method", sessionReference.getSessionId()));
        }catch (InvocationTargetException invocationTargetException){
            invocationTargetException.getCause().printStackTrace();
        }catch (PropagationException propagationException){
            propagationException.printStackTrace();
        }
    }
}

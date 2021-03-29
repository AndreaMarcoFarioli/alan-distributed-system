package distributedArchitecture;

import core.EventObject;
import core.ReturnableObject;
import exceptions.PropagationException;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteNode extends Remote {
    ReturnableObject<?> call(EventObject eventObject) throws RemoteException, NoSuchMethodException, PropagationException, IllegalAccessException, InvocationTargetException, Exception;
}

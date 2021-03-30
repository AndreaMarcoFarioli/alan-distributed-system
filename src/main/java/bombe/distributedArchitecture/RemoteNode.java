package bombe.distributedArchitecture;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.exceptions.PropagationException;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteNode extends Remote {
    ReturnableObject<?> call(EventObject eventObject) throws RemoteException, NoSuchMethodException, PropagationException, IllegalAccessException, InvocationTargetException, Exception;
}

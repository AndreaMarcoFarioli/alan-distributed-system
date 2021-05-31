package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteNode extends Remote {
    ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException;
}

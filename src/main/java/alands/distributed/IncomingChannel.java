package alands.distributed;

import alands.core.data.EventObject;
import alands.core.data.ReturnableObject;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IncomingChannel extends Remote {
    ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException;
}

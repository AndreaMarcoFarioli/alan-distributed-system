package alands.distributed;

import alands.core.data.EventObject;
import alands.core.data.ReturnableObject;

import java.rmi.RemoteException;

public interface InterComChannel extends RemoteNode{
    ReturnableObject<?> sendOver(EventObject eventObject) throws RemoteException, ReflectiveOperationException;
}

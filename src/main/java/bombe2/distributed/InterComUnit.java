package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

import java.rmi.RemoteException;

public interface InterComUnit extends RemoteNode{
    ReturnableObject<?> sendOver(EventObject eventObject) throws RemoteException, ReflectiveOperationException;
}

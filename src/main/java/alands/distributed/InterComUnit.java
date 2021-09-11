package alands.distributed;

import alands.core.data.EventObject;
import alands.core.data.ReturnableObject;
import alands.core.definitions.Propagator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class InterComUnit extends UnicastRemoteObject implements InterComChannel, RemoteNode{
    private final Propagator propagator;
    private final InterComChannel interComChannel;
    public InterComUnit(Propagator propagator, InterComChannel interComChannel) throws RemoteException {
        this.propagator = propagator;
        this.interComChannel = interComChannel;
    }

    @Override
    public ReturnableObject<?> sendOver(EventObject eventObject) throws RemoteException, ReflectiveOperationException {
        return interComChannel.sendOver(eventObject);
    }

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException {
        return propagator.propagate(eventObject);
    }
}

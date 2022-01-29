package alands.distributed;

import alands.core.data.EventObject;
import alands.core.data.ReturnableObject;
import alands.core.definitions.Propagator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CommunicationUnit extends UnicastRemoteObject implements OutgoingChannel, IncomingChannel {
    private final Propagator propagator;
    private final OutgoingChannel outgoingChannel;
    public CommunicationUnit(Propagator propagator, OutgoingChannel outgoingChannel) throws RemoteException {
        this.propagator = propagator;
        this.outgoingChannel = outgoingChannel;
    }

    @Override
    public ReturnableObject<?> sendOver(EventObject eventObject) throws RemoteException, ReflectiveOperationException {
        return outgoingChannel.sendOver(eventObject);
    }

    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException {
        return propagator.propagate(eventObject);
    }
}

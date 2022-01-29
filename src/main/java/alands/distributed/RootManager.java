package alands.distributed;

import alands.core.data.EventObject;
import alands.core.Manager;
import alands.core.data.ReturnableObject;
import alands.core.definitions.HasManager;
import alands.core.definitions.Propagator;

public final class RootManager implements Propagator, HasManager {
    private final Manager manager = new Manager();
    private OutgoingChannel outgoingChannel;

    private static RootManager instance = null;

    private RootManager() {
    }

    public static RootManager getInstance(){
        if (instance == null)
            instance = new RootManager();
        return instance;
    }

    public void init(OutgoingChannel interComUnit){
        if (this.outgoingChannel == null)
            this.outgoingChannel = interComUnit;
    }

    public OutgoingChannel getInterComChannel() {
        return outgoingChannel;
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public ReturnableObject<?> propagate(EventObject eventObject) throws ReflectiveOperationException {
        return getManager().propagate(eventObject);
    }
}
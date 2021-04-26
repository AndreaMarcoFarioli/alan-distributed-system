package bombe2.core;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.ServiceModel;
import bombe2.distributedArchitecture.HasManager;

public abstract class ExtendableService extends AbstractService implements HasManager {
    private final Manager manager = new Manager(this);

    public ExtendableService(String name, ServiceModel methods) {
        super(name, methods);
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public final ReturnableObject<?> propagate(EventObject eventObject) throws Exception {
        ReturnableObject<?> returnableObject;
        if (eventObject.hasNext()) {
            if (eventObject.isBottomUp()) {
                eventObject.getNext();
                returnableObject = getPropagator().propagate(eventObject);//bottomUp
            } else
                returnableObject = manager.propagate(eventObject);//topDown
        }
        else
            returnableObject = propagateInside(eventObject);//invocation
        return returnableObject;
    }

    @Override
    public Entity getEntity() {
        return super.getEntity();
    }

    @Override
    protected void onCreate() throws Exception {
        manager.create();
    }

    @Override
    protected void onDestroy()throws Exception {
        manager.destroy();
    }

    @Override
    protected void onStart()throws Exception {
        manager.start();
    }

    @Override
    protected void onStop()throws Exception {
        manager.stop();
    }

    @Override
    protected void onPause()throws Exception {
        manager.pause();
    }

    @Override
    protected void onResume()throws Exception {
        manager.resume();
    }

    protected void onRestart()throws Exception {
        manager.restart();
    }
}

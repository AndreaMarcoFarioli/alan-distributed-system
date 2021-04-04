package bombe.core;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.ServiceModel;
import bombe.distributedArchitecture.HasManager;

public abstract class ExtendableService extends AbstractService implements HasManager {
    private final Manager manager = new Manager();

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
        if (eventObject.hasNext())
            returnableObject = manager.propagate(eventObject);
        else
            returnableObject = propagateInside(eventObject);
        return returnableObject;
    }

    @Override
    public Entity getEntity() {
        return super.getEntity();
    }

    @Override
    public void onCreate() {
        manager.create();
    }

    @Override
    public void onDestroy() {
        manager.destroy();
    }

    @Override
    public void onStart() {
        manager.start();
    }

    @Override
    public void onStop() {
        manager.stop();
    }

    @Override
    public void onPause() {
        manager.pause();
    }

    @Override
    public void onResume() {
        manager.resume();
    }

    @Override
    public void onRestart() {
        manager.restart();
    }
}

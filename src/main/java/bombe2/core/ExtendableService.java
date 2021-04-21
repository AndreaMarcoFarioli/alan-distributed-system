package bombe2.core;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.ServiceModel;
import bombe2.distributedArchitecture.HasManager;

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
    public void onCreate() throws Exception {
        manager.create();
    }

    @Override
    public void onDestroy()throws Exception {
        manager.destroy();
    }

    @Override
    public void onStart()throws Exception {
        manager.start();
    }

    @Override
    public void onStop()throws Exception {
        manager.stop();
    }

    @Override
    public void onPause()throws Exception {
        manager.pause();
    }

    @Override
    public void onResume()throws Exception {
        manager.resume();
    }

    @Override
    public void onRestart()throws Exception {
        manager.restart();
    }
}

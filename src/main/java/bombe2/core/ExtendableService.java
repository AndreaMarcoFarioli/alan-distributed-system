package bombe2.core;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.HasManager;
import bombe2.exceptions.PropagationException;

/**
 * @author Andrea Marco Farioli
 * @version 0.1.0
 *
 */
public abstract class ExtendableService extends AbstractService implements HasManager {
    private final Manager manager = new Manager(this);

    public ExtendableService(String name) {
        super(name);
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public final ReturnableObject<?> propagate(EventObject eventObject) throws ReflectiveOperationException {
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
    protected void onCreate(){
        manager.create();
    }

    @Override
    protected void onDestroy(){
        manager.destroy();
    }

    @Override
    protected void onStart(){
        manager.start();
    }

    @Override
    protected void onStop(){
        manager.stop();
    }

    @Override
    protected void onPause(){
        manager.pause();
    }

    @Override
    protected void onResume(){
        manager.resume();
    }

    protected void onRestart(){
        manager.restart();
    }
}

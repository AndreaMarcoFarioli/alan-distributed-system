package bombe.core;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.EventPropagator;
import bombe.core.definitions.ServiceModel;
import bombe.distributedArchitecture.MainManager;
import bombe.exceptions.PropagationException;

public abstract class AbstractService implements EventPropagator {
    private final Entity entity;
    private final ServiceModel model;

    public AbstractService(String name, ServiceModel methods){
        entity = new Entity(name);
        this.model = methods;
    }

    protected final ReturnableObject<?> propagateInside(EventObject eventObject) throws Exception {
        if (eventObject.hasNext())
            throw new PropagationException("end services can't route = " + eventObject.getCoordinate());
        ReturnableObject<?> returnableObject;
        returnableObject =
                (ReturnableObject<?>) model.getClass()
                        .getMethod(eventObject.getMethod(), eventObject.getTypes())
                        .invoke(model, eventObject.getParams());
        return returnableObject;
    }

    public Entity getEntity() {
        return entity;
    }

    //region events
    protected void onCreate() throws Exception {

    }

    protected void onDestroy() throws Exception {

    }

    protected void onStart() throws Exception {

    }

    protected void onStop() throws Exception {

    }

    protected void onPause() throws Exception {

    }

    protected void onResume() throws Exception {

    }

    protected void onRestart() throws Exception {

    }
    //endregion

    public AbstractService searchServiceRoot(String name){
        return MainManager.
                getInstance().
                getManager().
                getService(name);
    }

    public ServiceModel getModel() {
        return model;
    }
}

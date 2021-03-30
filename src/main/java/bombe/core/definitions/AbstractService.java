package bombe.core.definitions;

import bombe.core.*;
import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.exceptions.PropagationException;

public abstract class AbstractService implements EventPropagator, EventInput {
    private final Identity identity;
    private final ServiceModel model;

    public AbstractService(String name, ServiceModel methods){
        identity = new Identity(name);
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

    public Identity getIdentity() {
        return identity;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }
}

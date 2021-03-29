package core;

import core.definitions.EventPropagator;
import core.definitions.ServiceModel;
import exceptions.PropagationException;

import java.lang.reflect.InvocationTargetException;

public abstract class Service implements EventPropagator {
    private final Identity identity;
    private final ServiceModel model;

    public Service(String name, ServiceModel methods){
        identity = new Identity(name);
        this.model = methods;
    }

    public Identity getIdentity() {
        return identity;
    }

    //region events
    protected void onCreate() {

    }

    protected void onDestroy() {

    }

    protected void onStart() {

    }

    protected void onStop() {

    }

    protected void onPause() {

    }

    protected void onResume() {

    }

    protected void onRestart() {

    }

    //endregion

    @Override
    public ReturnableObject<?> propagate(EventObject eventObject) throws Exception {
        if (eventObject.hasNext())
            throw new PropagationException("end services can't route = " + eventObject.getCoordinate());
        ReturnableObject<?> returnableObject;
            returnableObject =
                    (ReturnableObject<?>) model.getClass()
                            .getMethod(eventObject.getMethod(), eventObject.getTypes())
                            .invoke(model, eventObject.getParams());
            return returnableObject;
    }
}

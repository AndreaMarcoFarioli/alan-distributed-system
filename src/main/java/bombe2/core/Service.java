package bombe2.core;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

public abstract class Service extends AbstractService {
    public Service(String name){
        super(name);
    }
    @Override
    public final ReturnableObject<?> propagate(EventObject eventObject) throws Exception {
        ReturnableObject<?> returnableObject;
        if (eventObject.hasNext() && eventObject.isBottomUp()) {
            eventObject.getNext();
            returnableObject = getPropagator().propagate(eventObject);
        }else
            returnableObject = propagateInside(eventObject);
        return returnableObject;
    }

}

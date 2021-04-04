package bombe.core;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.ServiceModel;

public abstract class Service extends AbstractService {
    public Service(String name, ServiceModel methods){
        super(name, methods);
    }
    //region events


    //endregion
    @Override
    public final ReturnableObject<?> propagate(EventObject eventObject) throws Exception {
        return propagateInside(eventObject);
    }

}

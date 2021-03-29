package core.definitions;

import core.EventObject;
import core.ReturnableObject;

public interface EventPropagator {
    ReturnableObject<?> propagate(EventObject eventObject) throws Exception;
}
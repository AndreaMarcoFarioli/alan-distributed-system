package bombe.core.definitions;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;

public interface EventPropagator {
    ReturnableObject<?> propagate(EventObject eventObject) throws Exception;
}
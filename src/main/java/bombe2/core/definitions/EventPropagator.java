package bombe2.core.definitions;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

public interface EventPropagator {
    ReturnableObject<?> propagate(EventObject eventObject) throws Exception;
}
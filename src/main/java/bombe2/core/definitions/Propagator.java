package bombe2.core.definitions;

import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

/**
 * Interfaccia utilizzata per descrivere, indipendentemente dalla classe, il metodo di propagazione degli eventi.
 */
public interface Propagator {
    ReturnableObject<?> propagate(EventObject eventObject) throws Exception;
}
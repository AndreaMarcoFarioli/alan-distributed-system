package alands.core.definitions;

import alands.core.data.EventObject;
import alands.core.data.ReturnableObject;

/**
 * Interfaccia utilizzata per descrivere, indipendentemente dalla classe, il metodo di propagazione degli eventi.
 */
public interface Propagator {
    ReturnableObject<?> propagate(EventObject eventObject) throws ReflectiveOperationException;
}
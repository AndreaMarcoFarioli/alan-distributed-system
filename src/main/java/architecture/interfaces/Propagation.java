package architecture.interfaces;

import architecture.EventObject;
import architecture.ReturnableObject;

public interface Propagation {
    ReturnableObject propagate(EventObject eventObject);
}

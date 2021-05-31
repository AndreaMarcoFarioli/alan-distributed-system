package alands.distributed;

import alands.core.SessionReference;
import alands.core.data.EventObject;
import alands.exceptions.MalformedEventException;
import org.apache.commons.lang3.ArrayUtils;

public final class SessionProvider {
    private final SessionReference sessionReference;

    public SessionProvider(){
        this(null);
    }

    public SessionProvider(SessionReference sessionReference){
        this.sessionReference = sessionReference;
    }

    public SessionReference getSessionReference() {
        return sessionReference.getReference();
    }

    public EventObject generateEventObject(String coordinate, Object... params) throws MalformedEventException {
        return new EventObject(coordinate, ArrayUtils.addAll(new Object[]{this}, params));
    }

    public void destroy(){
        sessionReference.destroy();
    }
}
package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.EventObject;
import bombe2.core.definitions.ISessionManager;
import bombe2.exceptions.MalformedEventException;
import bombe2.exceptions.NoSuchSessionException;
import bombe2.exceptions.SessionException;
import org.apache.commons.lang3.ArrayUtils;

public class SessionProvider {
    private static final ISessionManager sessionManager = new SessionManager();
    private final SessionReference sessionReference;

    public SessionProvider(){
        this(null);
    }

    public SessionProvider(String sessionId){
        if (sessionId == null)
            this.sessionReference = sessionManager.createSession();
        else if (sessionManager.available(sessionId))
            this.sessionReference = sessionManager.getSession(sessionId);
        else
            throw new NoSuchSessionException("Session not found at " + sessionId);
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
package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.EventObject;
import bombe2.exceptions.MalformedEventException;
import bombe2.exceptions.NoSuchSessionException;
import org.apache.commons.lang3.ArrayUtils;

public class SessionProvider {
    private final static SessionManager sessionManager = new SessionManager();
    private final SessionReference sessionReference;

    public SessionProvider(){
        this(null);
    }

    public SessionProvider(String sessionId){
        if (sessionId == null)
            sessionReference = sessionManager.createSession();
        else if (sessionManager.available(sessionId))
            sessionReference = sessionManager.getSession(sessionId);
        else
            throw new NoSuchSessionException("Doesn't exist session with " + sessionId);
    }

    public SessionReference getSessionReference() {
        return sessionReference;
    }

    public EventObject generateEventObject(String coordinate, Object... params) throws MalformedEventException{
        params = ArrayUtils.addAll(new Object[]{this}, params);
        return new EventObject(coordinate, params);
    }

    public void destroy(){
        sessionReference.destroy();
    }

    public static int getSessionCount(){
        return sessionManager.sessionCount();
    }
}

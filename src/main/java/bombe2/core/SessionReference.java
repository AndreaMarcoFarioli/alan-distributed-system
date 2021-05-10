package bombe2.core;

import bombe2.exceptions.SessionException;

public class SessionReference {
    private boolean opened = true;
    private final SessionManager sessionManager;
    private final String sessionId;
    //private final DataTable dataTable = new DatabaseVarT("");

    public SessionReference(SessionManager sessionManager){
        this(sessionManager, sessionManager.generateSessionId());
    }

    protected SessionReference(SessionManager sessionManager, String sessionId){
        this.sessionManager = sessionManager;
        this.sessionId = sessionId;
    }

    public void destroy(){
        sessionManager.destroySession(sessionId);
        opened = false;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean isOpened() {
        return opened;
    }

    public SessionReference getReference(){
        if (!opened)
            throw new SessionException("Session " + sessionId + " is closed");
        return this;
    }
}

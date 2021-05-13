package bombe2.core;

import bombe2.core.data.Storage;
import bombe2.exceptions.NoSuchSessionException;
import bombe2.exceptions.SessionException;

public abstract class SessionReference {
    private boolean opened = true;
    private final SessionManager sessionManager;
    private final String sessionId;
    private Storage storage;

    protected SessionReference(SessionManager sessionManager, String sessionId, Storage storage){
        this.sessionManager = sessionManager;
        this.sessionId = sessionId;
        this.storage = storage;
    }

    protected void setStorage(Storage storage) {
        if (getReference().storage == null)
            this.storage = storage;
    }

    public void destroy(){
        getReference().sessionManager.destroySession(sessionId);
        opened = false;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean isOpened() {
        return opened;
    }

    public final SessionReference getReference(){
        if (!isOpened() || !sessionManager.available(sessionId))
            throw new SessionException("Session " + sessionId + " is closed or does not exist anymore");
        return this;
    }

    public Storage getStorage() {
        return getReference().storage;
    }

    @Override
    public String toString() {
        return "SessionReference{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
    
}

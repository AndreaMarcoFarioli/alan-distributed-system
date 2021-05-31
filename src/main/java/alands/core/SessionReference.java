package alands.core;

import alands.core.data.ISessionReference;
import alands.core.data.Storage;
import alands.exceptions.SessionException;

public abstract class SessionReference implements ISessionReference {
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

    @Override
    public void destroy(){
        getReference().sessionManager.destroySession(getSessionId());
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public boolean isOpened() {
        boolean opened = true;
        try {
            getReference();
        }catch (SessionException sessionException){
            opened = false;
            sessionException.printStackTrace();
        }
        return opened;
    }

    @Override
    public final SessionReference getReference(){
        if (!sessionManager.available(getSessionId()))
            throw new SessionException("Session " + sessionId + " is closed or does not exist anymore");
        return this;
    }

    @Override
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

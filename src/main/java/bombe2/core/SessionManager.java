package bombe2.core;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class SessionManager {
    public abstract SessionReference createSession();

    public String generateSessionId(){
        return DigestUtils.sha256Hex(Long.toString(System.nanoTime()));
    }

    public abstract SessionReference getSession(String sessionId);

    public boolean available(String sessionId){
        SessionReference sessionReference;
        boolean available = (sessionReference = getSession(sessionId)) != null;
        if (available)
            available = sessionReference.isOpened();
        return available;
    }

    public abstract void destroySession(String sessionId);

    public abstract int sessionCount();
}

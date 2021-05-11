package bombe2.core;

import bombe2.core.definitions.ISessionManager;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class SessionManager implements ISessionManager {
    private final Map<String,SessionReference> sessionMap =  new HashMap<>();


    @Override
    public SessionReference createSession(){
        SessionReference sessionReference = new SessionReference(this);
        sessionMap.put(sessionReference.getSessionId(), sessionReference);
        return sessionReference;
    }

    public static String generateSessionId(){
        return DigestUtils.sha256Hex(Long.toString(System.nanoTime()));
    }

    @Override
    public SessionReference getSession(String sessionId){
        return sessionMap.get(sessionId);
    }

    @Override
    public boolean available(String sessionId){
        SessionReference sessionReference;
        boolean available = (sessionReference = sessionMap.get(sessionId)) != null;
        if (available)
            available = sessionReference.isOpened();
        return available;
    }

    @Override
    public void destroySession(String sessionId){
        sessionMap.remove(sessionId);
    }

    @Override
    public int sessionCount(){
        return sessionMap.size();
    }
}

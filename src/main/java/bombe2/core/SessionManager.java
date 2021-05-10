package bombe2.core;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SessionManager {
    private final Map<String,SessionReference> sessionMap =  new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public void destroySession(String sessionId){
        sessionMap.remove(sessionId);
    }

    public SessionReference createSession(){
        SessionReference sessionReference = new SessionReference(this);
        sessionMap.put(sessionReference.getSessionId(), sessionReference);
        return sessionReference;
    }

    public String generateSessionId(){
        String sessionId = DigestUtils.sha256Hex(System.nanoTime()+"");
        return sessionId;
    }

    public SessionReference getSession(String sessionId){
        return sessionMap.get(sessionId);
    }

    public boolean available(String sessionId){
        SessionReference sessionReference;
        boolean available = (sessionReference = sessionMap.get(sessionId)) != null;
        if (available)
            available = sessionReference.isOpened();
        return available;
    }

    public int sessionCount(){
        return sessionMap.size();
    }
}

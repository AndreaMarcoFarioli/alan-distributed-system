package bombe2.alpha;

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
        String sessionId = Integer.toString(counter.incrementAndGet());
        return sessionId;
    }

    public SessionReference getSession(String sessionId){
        return sessionMap.get(sessionId);
    }
}

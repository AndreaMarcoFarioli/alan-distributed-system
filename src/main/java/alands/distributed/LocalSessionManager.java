package alands.distributed;

import alands.core.SessionManager;
import alands.core.SessionReference;

import java.util.HashMap;
import java.util.Map;

public final class LocalSessionManager extends SessionManager {
    private final Map<String, SessionReference> sessionMap = new HashMap<>();

    @Override
    public SessionReference createSession() {
        String sessionId = generateSessionId();
        SessionReference sessionReference = new LocalSessionReference(this, sessionId);
        sessionMap.put(sessionId, sessionReference);
        return sessionReference;
    }

    @Override
    public SessionReference getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    @Override
    public void destroySession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    @Override
    public int sessionCount() {
        return sessionMap.size();
    }

    @Override
    public void destroyAll() {

    }
}

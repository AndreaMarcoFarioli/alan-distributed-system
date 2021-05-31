package alands.core.data;

import alands.core.SessionReference;

public interface ISessionManager {
    int sessionCount();
    void destroyAll();
    void destroySession(String sessionId);
    boolean available(String sessionId);
    SessionReference getSession(String sessionId);
    String generateSessionId();
    SessionReference createSession();
}

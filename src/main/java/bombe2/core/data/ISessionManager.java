package bombe2.core.data;

import bombe2.core.SessionReference;

public interface ISessionManager {
    int sessionCount();
    void destroyAll();
    void destroySession(String sessionId);
    boolean available(String sessionId);
    SessionReference getSession(String sessionId);
    String generateSessionId();
    SessionReference createSession();
}

package bombe2.core.definitions;

import bombe2.core.SessionReference;

public interface ISessionManager {
    SessionReference createSession();

    SessionReference getSession(String sessionId);

    boolean available(String sessionId);

    void destroySession(String sessionId);

    int sessionCount();
}

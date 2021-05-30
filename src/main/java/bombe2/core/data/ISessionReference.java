package bombe2.core.data;

import bombe2.core.SessionReference;

public interface ISessionReference {
    void destroy();
    String getSessionId();
    boolean isOpened();
    SessionReference getReference();
    Storage getStorage();
}

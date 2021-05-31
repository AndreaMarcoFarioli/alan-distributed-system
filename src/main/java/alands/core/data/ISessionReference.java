package alands.core.data;

import alands.core.SessionReference;

public interface ISessionReference {
    void destroy();
    String getSessionId();
    boolean isOpened();
    SessionReference getReference();
    Storage getStorage();
}

package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;

public class DatabaseSessionReference extends SessionReference {
    public DatabaseSessionReference(SessionManager sessionManager, String sessionId) {
        super(sessionManager, sessionId, null);
        setStorage(new SessionDatabaseStorage(this));
    }

    @Override
    public boolean isOpened() {
        return true;
    }
}

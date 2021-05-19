package bombe2.distributed.database_implementation;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;

public final class DatabaseSessionReference extends SessionReference {
    public DatabaseSessionReference(SessionManager sessionManager, String sessionId) {
        super(sessionManager, sessionId, null);
        setStorage(new SessionDatabaseStorage(this));
    }
}

package alands.distributed.database_implementation;

import alands.core.SessionManager;
import alands.core.SessionReference;

@Deprecated
public final class DatabaseSessionReference extends SessionReference {
    public DatabaseSessionReference(SessionManager sessionManager, String sessionId) {
        super(sessionManager, sessionId, null);
        setStorage(new SessionDatabaseStorage(this));
    }
}

package alands.distributed;

import alands.core.SessionManager;
import alands.core.SessionReference;
import alands.core.data.LocalStorage;

public final class LocalSessionReference extends SessionReference {
    public LocalSessionReference(SessionManager sessionManager, String sessionId) {
        super(sessionManager, sessionId, new LocalStorage());
    }
}

package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.LocalStorage;
import bombe2.core.data.Storage;

public final class LocalSessionReference extends SessionReference {
    public LocalSessionReference(SessionManager sessionManager, String sessionId) {
        super(sessionManager, sessionId, new LocalStorage());
    }
}

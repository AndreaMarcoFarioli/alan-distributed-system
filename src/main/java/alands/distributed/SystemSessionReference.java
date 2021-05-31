package alands.distributed;

import alands.core.SessionManager;
import alands.core.SessionReference;
import alands.core.data.LocalStorage;

public final class SystemSessionReference extends SessionReference {
    private static SystemSessionReference sessionReference;
    private SystemSessionReference(SessionManager sessionManager){
        super(sessionManager, sessionManager.createSession().getSessionId(), new LocalStorage());
    };

    public static SessionReference getInstance(){

        if (sessionReference == null)
            sessionReference = new SystemSessionReference(new LocalSessionManager());
        return sessionReference;
    }

    @Override
    public void destroy() {}
}

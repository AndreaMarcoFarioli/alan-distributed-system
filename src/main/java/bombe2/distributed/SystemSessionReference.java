package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.LocalStorage;

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

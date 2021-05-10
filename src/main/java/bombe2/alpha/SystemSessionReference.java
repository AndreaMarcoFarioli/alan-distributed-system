package bombe2.alpha;

import bombe2.exceptions.SessionException;

public class SystemSessionReference extends SessionReference {
    private static SystemSessionReference sessionReference;
    private static SessionManager sessionManager;
    private SystemSessionReference(SessionManager sessionManager){
        super(sessionManager);
    };

    public static SessionReference getInstance(){

        if (sessionManager == null)
            throw new SessionException();

        if (sessionReference == null)
            sessionReference = new SystemSessionReference(sessionManager);
        return sessionReference;
    }

    public static void setSessionManager(SessionManager sessionManager) {
        SystemSessionReference.sessionManager = sessionManager;
    }

    @Override
    public void destroy() {}
}

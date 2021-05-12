package bombe2.distributed;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;

public class SystemSessionReference extends SessionReference {
    private static SystemSessionReference sessionReference;
    private SystemSessionReference(SessionManager sessionManager){
        super(sessionManager, "", null);
    };

    public static SessionReference getInstance(){

        if (sessionReference == null)
            sessionReference = new SystemSessionReference(null);
        return sessionReference;
    }

    @Override
    public void destroy() {}
}

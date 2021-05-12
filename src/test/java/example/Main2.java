package example;

import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.distributed.LocalSessionManager;

public class Main2 {
    public static void main(String[] args) throws RuntimeException{
        SessionManager databaseSessionManager = new LocalSessionManager();
        SessionReference sessionReference = databaseSessionManager.createSession();
        System.out.println(databaseSessionManager.sessionCount());
        System.out.println(sessionReference.getStorage());
        sessionReference.getStorage().setParameter("ciao", "pirro");
        sessionReference.getStorage().setParameter("ciccio", "maiale");
        System.out.println(sessionReference.getStorage().getParameter("ciccio", null));
        databaseSessionManager.destroySession(sessionReference.getSessionId());
        sessionReference.destroy();
    }
}

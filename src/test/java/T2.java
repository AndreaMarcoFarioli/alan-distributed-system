import bombe2.core.SessionManager;
import bombe2.core.SessionReference;
import bombe2.core.data.Storage;
import bombe2.distributed.remote_data.ReferencedSessionManager;

public class T2 {
    public static void main(String[] args) {
        SessionManager referencedSessionManager =
                new ReferencedSessionManager("localhost", 1099, "remoteSessionManager");
        SessionReference sessionReference = referencedSessionManager.createSession();
        System.out.println(sessionReference.getSessionId());
        Storage storage = sessionReference.getStorage();
        sessionReference.destroy();
        storage.setParameter("ciccio", "andrea");
        System.out.println(storage.getParameter("ciccio", String.class));
    }
}

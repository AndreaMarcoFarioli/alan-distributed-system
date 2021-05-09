package bombe2.alpha;

public class SessionReference {
    private boolean opened = true;
    private final SessionManager sessionManager;
    private final String sessionId;
    //private final DataTable dataTable = new DatabaseVarT("");

    public SessionReference(SessionManager sessionManager){
        this.sessionManager = sessionManager;
        sessionId = this.sessionManager.generateSessionId();
    }

    public void destroy(){
        sessionManager.destroySession(sessionId);
        opened = false;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean isOpened() {
        return opened;
    }
}

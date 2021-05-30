package bombe2.distributed;

import bombe2.core.SessionReference;
import bombe2.core.data.DatabaseStorage;


public final class SessionDatabaseStorage extends DatabaseStorage {
    private final SessionReference sessionReference;

    private static final String
            insertQuery = "INSERT INTO session_vars (name, val, sid_id) VALUES(?, ?, (SELECT id FROM session_storage WHERE sid = \"%s\")) ON DUPLICATE KEY UPDATE val=?",
            getQuery= "SELECT val FROM session_vars WHERE name=? AND sid_id=(SELECT id FROM session_storage WHERE sid = \"%s\")";

    public SessionDatabaseStorage(SessionReference sessionReference){
        this.sessionReference = sessionReference;
    }

    private String getSessionId() {
        return sessionReference.getReference().getSessionId();
    }

    @Override
    public String getGetQuery() {
        return String.format(getQuery, getSessionId());
    }

    @Override
    public String getInsertQuery() {
        return String.format(insertQuery, getSessionId());
    }
}

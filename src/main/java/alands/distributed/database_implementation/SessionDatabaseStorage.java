package alands.distributed.database_implementation;

import alands.core.SessionReference;


public final class SessionDatabaseStorage extends DatabaseStorage {
    private final SessionReference sessionReference;

    private static final String
            insertQuery = "INSERT INTO session_var (name, val, sid_id) VALUES(?, ?, (SELECT id FROM session WHERE sid=\"%s\")) ON DUPLICATE KEY UPDATE val=?",
            getQuery= "SELECT val FROM session_var WHERE name=? AND sid_id= (SELECT id FROM session WHERE sid=\"%s\")";

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

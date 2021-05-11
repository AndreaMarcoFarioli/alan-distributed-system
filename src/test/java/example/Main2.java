package example;

import bombe2.core.data.DatabaseStorage;
import bombe2.core.data.Storage;
import bombe2.distributed.SessionDatabaseStorage;
import bombe2.distributed.SessionProvider;

public class Main2 {
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        Storage databaseParameter = new SessionDatabaseStorage(sessionProvider.getSessionReference());
        databaseParameter.setParameter("ciao", "paolo");
        sessionProvider.destroy();
        System.out.println(databaseParameter.getParameter("ciao", String.class));
        //databaseParameter.clear("ciao");
    }
}

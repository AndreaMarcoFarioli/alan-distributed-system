import bombe2.distributed.DatabaseSessionManager;

public class Main {
    public static void main(String[] args) {
        DatabaseSessionManager databaseSessionManager = new DatabaseSessionManager();
        databaseSessionManager.createSession();
        System.out.println("ciao");
    }
}

package program;

import bombe.core.ExtendableService;
import bombe.core.Manager;
import bombe.core.data.EventObject;
import bombe.distributedArchitecture.MainManager;

public class ServizioA extends ExtendableService {
    public ServizioA(Manager parentManager) {
        super("servizioa", new Model(), parentManager);
    }

    @Override
    public void onCreate() throws Exception {
        super.onCreate();
        long t1 = System.currentTimeMillis();
        try {
            System.out.println(
                    MainManager.getInstance().sendOver(new EventObject("master#serviziob:metodoA"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        long t2 = System.currentTimeMillis();

        System.out.println(t2-t1 + "ms");
    }
}

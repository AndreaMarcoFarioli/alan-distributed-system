package program;

import bombe2.core.ExtendableService;
import bombe2.core.data.EventObject;
import bombe2.distributedArchitecture.MainManager;

public class ServizioA extends ExtendableService {
    public ServizioA() {
        super("servizioa", new Model());
    }

    @Override
    public void onCreate() throws Exception {
        super.onCreate();
        long t1 = System.currentTimeMillis();
        try {
            System.out.println(MainManager.getInstance().sendOver(new EventObject("master#serviziob:metodoA")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1 + "ms");
    }
}

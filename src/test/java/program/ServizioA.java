package program;

import bombe.core.ExtendableService;

public class ServizioA extends ExtendableService {
    public ServizioA() {
        super("servizioa", new Model());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("creation");
    }
}

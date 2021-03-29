package program;

import core.Service;
import core.definitions.ExtendableService;

public class ServizioA extends ExtendableService {
    public ServizioA() {
        super("servizioa", new Model());
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        System.out.println("creation");
    }
}

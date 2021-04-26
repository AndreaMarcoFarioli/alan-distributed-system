package program;

import bombe2.annotations.MethodVisibility;
import bombe2.core.ExtendableService;

public class ServizioA extends ExtendableService {
    public ServizioA() {
        super("servizioa", new Model());
    }

    @Override
    public void onCreate() throws Exception {
        super.onCreate();

    }
    //TODO Change Reflection for method
    @MethodVisibility()
    public void metodoA(){

    }
}

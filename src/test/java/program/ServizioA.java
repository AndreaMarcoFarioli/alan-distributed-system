package program;

import bombe2.annotations.MethodVisibility;
import bombe2.core.ExtendableService;

public class ServizioA extends ExtendableService {
    public ServizioA() {
        super("servizioa", new Model());
        getManager().addService(new ServizioB());
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

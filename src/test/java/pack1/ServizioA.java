package pack1;

import architecture.ExtendableService;

public class ServizioA extends ExtendableService {
    public ServizioA(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        System.out.println("ciao");
        super.onCreate();
    }
}

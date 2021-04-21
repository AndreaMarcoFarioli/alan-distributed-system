package bombe2.distributedArchitecture.defaultServices;

import bombe2.core.Service;
import bombe2.distributedArchitecture.MainManager;

public class AutoLoad extends Service {
    public AutoLoad() {
        super("autoload", null);
    }

    @Override
    public void onCreate() throws Exception {
        super.onCreate();
        MainManager.getInstance().getManager().create();
    }
}
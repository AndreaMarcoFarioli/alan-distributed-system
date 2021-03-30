package bombe.distributedArchitecture.defaultServices;

import bombe.core.Service;
import bombe.distributedArchitecture.MainManager;

public class AutoLoad extends Service {
    public AutoLoad() {
        super("autoload", null);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MainManager.getInstance().getManager().create();
    }
}
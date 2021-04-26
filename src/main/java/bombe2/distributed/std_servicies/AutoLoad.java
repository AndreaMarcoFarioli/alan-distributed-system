package bombe2.distributed.std_servicies;

import bombe2.core.Service;
import bombe2.distributed.MainManager;

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
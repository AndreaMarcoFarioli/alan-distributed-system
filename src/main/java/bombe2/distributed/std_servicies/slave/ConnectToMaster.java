package bombe2.distributed.std_servicies.slave;

import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.distributed.MainManager;

public class ConnectToMaster extends Service {
    public ConnectToMaster() {
        super("connect-to-master");
    }

    @Override
    protected void onCreate() throws Exception {
        super.onCreate();
        MainManager.getInstance().sendOver(new EventObject("master#node-manager:register"));
    }
}

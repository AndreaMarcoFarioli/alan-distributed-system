package bombe.distributedArchitecture.defaultServices.slave;

import bombe.core.Service;
import bombe.core.data.EventObject;
import bombe.distributedArchitecture.MainManager;

public class ConnectToMaster extends Service {
    public ConnectToMaster() {
        super("connect-to-master", null);
    }

    @Override
    protected void onCreate() throws Exception {
        super.onCreate();
        MainManager.getInstance().sendOver(new EventObject("master#node-manager:register"));
    }
}

package bombe2.distributedArchitecture.defaultServices.slave;

import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.distributedArchitecture.MainManager;

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

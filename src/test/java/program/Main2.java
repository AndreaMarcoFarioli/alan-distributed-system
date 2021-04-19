package program;

import bombe.core.data.ClusterEnvironment;
import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.Env;
import bombe.distributedArchitecture.MainManager;
import bombe.distributedArchitecture.RemoteNode;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main2 {
    public static void main(String[] args) throws Exception {
        //  new NodeProviderDemo();
        ClusterEnvironment.getInstance();
        Env.getInstance().set("host/master", "localhost");
        Env.getInstance().set("port/master", 1099);
        Env.getInstance().set("port/slave", 1099);
        new SlaveToMaster();
        MainManager.getInstance().getManager().addService(new ServizioA());
        MainManager.getInstance().getManager().create();
    }
}

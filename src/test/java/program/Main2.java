package program;

import bombe.core.ExtendableService;
import bombe.core.Service;
import bombe.core.data.ClusterEnvironment;
import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.core.definitions.Env;
import bombe.distributedArchitecture.MainManager;
import bombe.distributedArchitecture.RemoteNode;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) throws Exception {
        //  new NodeProviderDemo();

        Env env = new ClusterEnvironment();
        env.set("host/master", "localhost");
        env.set("port/master", 1099);
        env.set("port/slave", 1099);

        //TODO Trovare un modo per trovare una forma efficace di inizializzazione
        new SlaveToMaster();
        MainManager.getInstance().setEnv(env);
        MainManager.getInstance().getManager().addService(ServizioA.class);
        MainManager.getInstance().getManager().create();
    }
}

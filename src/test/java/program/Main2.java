package program;

import bombe2.core.data.ClusterEnvironment;
import bombe2.distributedArchitecture.MainManager;

public class Main2 {
    public static void main(String[] args) throws Exception {
        new NodeProviderDemo();
        ClusterEnvironment.getInstance().set("host/master", "localhost");
        ClusterEnvironment.getInstance().set("port/master", 1099);

        MainManager.getInstance().getManager().addService(new ServizioA());
        MainManager.getInstance().getManager().create();
    }
}

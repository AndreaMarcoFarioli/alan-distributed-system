package program;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import bombe.distributedArchitecture.MainManager;
import bombe.distributedArchitecture.RemoteNode;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main2 {
    public static void main(String[] args) throws Exception {
        new NodeProviderDemo();
        MainManager.getInstance().getManager().addService(new ServizioA());
        MainManager.getInstance().getManager().create();
    }
}

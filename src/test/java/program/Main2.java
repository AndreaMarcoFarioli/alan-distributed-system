package program;

import core.EventObject;
import core.ReturnableObject;
import distributedArchitecture.RemoteNode;
import exceptions.MalformedEventException;
import exceptions.PropagationException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main2 {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        RemoteNode remoteNode = (RemoteNode) registry.lookup("main");
        long t1 = System.currentTimeMillis();
        int a = ((ReturnableObject<Integer>)remoteNode.call(new EventObject("servizioa.serviziob:metodoA"))).getData();
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println(a);
    }
}

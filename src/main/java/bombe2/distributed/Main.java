package bombe2.distributed;

import bombe2.core.data.EventObject;
import bombe2.distributed.*;
import bombe2.exceptions.MalformedEventException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static int port = 1000 + (int)(Math.random()*100);
    public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedEventException, ReflectiveOperationException, InterruptedException {
        DBComUnit interComUnit = new DBComUnit(RootManager.getInstance());
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind("com", interComUnit);
        RootManager.getInstance().init(interComUnit);
        Timer t = new Timer();
        RootManager.getInstance().getManager().addService(new ServizioA());
        System.out.println(port+" at");
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    RootManager.getInstance().sendOver(new EventObject("ser:me", port));
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (MalformedEventException e) {
                    e.printStackTrace();
                }

            }
        },5000, (int)(5000 * Math.random()));

    }
}
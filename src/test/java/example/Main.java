package example;

import bombe2.alpha.SessionReference;
import bombe2.core.Manager;
import bombe2.core.data.EventObject;
import bombe2.distributed.MainManager;
import bombe2.exceptions.MalformedEventException;
import bombe2.exceptions.PropagationException;
import bombe_connector.ClasseA;
import bombe_connector.ClasseB;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;
import test_services.ServiceTest;

import javax.sound.sampled.AudioFormat;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.sql.Struct;
import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedEventException, ReflectiveOperationException, RemoteException {
        Manager manager = MainManager.getInstance().getManager();
        manager.addService(new ServiceTest());
        SessionReference sessionReference = EventObject.createSession();
        try {
            MainManager.getInstance().call(new EventObject("test:method", sessionReference.getSessionId()));
        }catch (InvocationTargetException invocationTargetException){
            invocationTargetException.getCause().printStackTrace();
        }catch (PropagationException propagationException){
            propagationException.printStackTrace();
        }
    }
}

package example;

import bombe2.core.AbstractService;
import bombe2.core.Manager;
import bombe2.core.data.EventObject;
import bombe2.distributed.MainManager;

import java.io.File;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        //System.out.println(System.getenv());
        File file = new File(System.getenv("SERVICES_PATH"));
        System.out.println(file.getAbsoluteFile());
        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
        Class<?> class3=classLoader.loadClass("Service");
        System.out.println(Arrays.toString(class3.getDeclaredMethods()));
    }
}

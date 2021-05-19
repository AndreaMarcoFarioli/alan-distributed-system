import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.RootManager;
import bombe2.exceptions.MalformedEventException;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MathService extends Service {

    public MathService() {
        super("math");
    }

    @MethodVisibility(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> sum(Integer n){
        final AtomicLong atomicInteger = new AtomicLong();
        final ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i< n; i++){
            threadPool.submit(() -> {
                try {
                    long integer =
                            (long) RootManager
                                    .getInstance()
                                    .sendOver(new EventObject("math:iterate", 10000))
                                    .getData();
                    atomicInteger.addAndGet(integer);
                } catch (ReflectiveOperationException | RemoteException | MalformedEventException reflectiveOperationException) {
                    reflectiveOperationException.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(10, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ReturnableObject<>(atomicInteger);
    }

    @MethodVisibility(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> iterate(Integer n){
        long integer = 0;
        for (int i = 0; i < n; i++){
            integer += i;
        }
        return new ReturnableObject<>(integer);
    }


    @MethodVisibility(visibility = VisibilityType.PROTECTED)
    public ReturnableObject<?> test(Integer port){
        System.out.println(port);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}

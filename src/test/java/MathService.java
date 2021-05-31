import bombe2.annotations.ExportMethod;
import bombe2.annotations.VisibilityType;
import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.RootManager;

import java.util.ArrayList;
import java.util.concurrent.*;

public class MathService extends Service {
    ExecutorService executorService = Executors.newCachedThreadPool();
    public MathService() {
        super("math");
    }

    @ExportMethod(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> benchmark(Long max, Long size){
        long integerPart = max/size;
        long rest = max-integerPart*size;
        int dimReq = rest == 0? 0:1;
        ArrayList<Callable<Object>> prepare = new ArrayList<>();
        for (long i = 0; i < integerPart + dimReq; i++){
            final long e = rest!=0&&i==0?rest:size;
            //ystem.out.println("E = "+e);
            prepare.add(()->this.callExec(e));
        }

        try {
            executorService.invokeAll(prepare);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private int callExec(long dim){
        int o = -1;
        try{
            o = (Integer)RootManager.getInstance().getInterComChannel().sendOver(new EventObject("math:exec", dim)).getData();
        }catch (Exception ignored){}
        return o;
    }

    @ExportMethod(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> exec(Long dim){
        int res = 0;
        for (long i = 0; i < dim; i++){
            res++;
        }
        return new ReturnableObject<>(res);
    }

}

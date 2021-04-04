package program;

import bombe.core.data.ReturnableObject;
import bombe.core.definitions.ServiceModel;

public class Model implements ServiceModel {
    public ReturnableObject<Integer> metodoA(){
        int integer = 0;
        for (int i = 0; i < 10; i++)
            integer += integer + 1;
        System.out.println(Main.integer.incrementAndGet());
        return new ReturnableObject<>(integer);
    }
}

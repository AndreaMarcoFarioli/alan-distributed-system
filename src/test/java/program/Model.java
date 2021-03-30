package program;

import bombe.core.data.ReturnableObject;
import bombe.core.definitions.ServiceModel;

public class Model implements ServiceModel {
    public ReturnableObject<Integer> metodoA(){
        int integer = 0;
        for (int i = 0; i < 10; i++)
            integer += integer + 1;
        return new ReturnableObject<>(integer);
    }
}

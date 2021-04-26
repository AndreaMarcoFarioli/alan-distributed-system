package program;

import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.ServiceModel;

public class Model implements ServiceModel {
    public ReturnableObject<Integer> metodoA(){
        System.out.println("Senti");
        return null;
    }
    public ReturnableObject<Integer> metodoB(){
        System.out.println("funziono");
        return null;
    }
    public ReturnableObject<Integer> metodoC(){
        System.out.println("funzionavo");
        return null;
    }
}

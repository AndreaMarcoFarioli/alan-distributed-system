package bombe2.distributed;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.Service;

public class ServizioA extends Service {

    public ServizioA() {
        super("ser");
    }

    @MethodVisibility(visibility = VisibilityType.PUBLIC)
    public void me(Integer integer){
        System.out.println(integer);
    }
}

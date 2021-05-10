package test_services;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

public class ServiceTest extends Service {
    public ServiceTest() {
        super("test");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("ciao");
    }

    @MethodVisibility(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> method(EventObject eventObject) throws Exception {
        throw new Exception("ciccio");
    }

}

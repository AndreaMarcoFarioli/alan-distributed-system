package example;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.Service;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

public class SubService extends Service {
    public SubService() {
        super("subService");
    }

    @Override
    protected void onManagerAdded() {
        super.onManagerAdded();
    }

    @MethodVisibility(visibility = VisibilityType.PROTECTED)
    public ReturnableObject<?> method1(EventObject eventObject, Integer a, Integer b){
        System.out.println(a+b);
        System.out.println(getPath());
        return new ReturnableObject<>(a+b);
    }
}

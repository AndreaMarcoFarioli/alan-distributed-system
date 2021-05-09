package example;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.ExtendableService;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

public class ExampleService extends ExtendableService implements IExampleServiceMethods {
    public ExampleService() {
        super("exampleService");
    }

    @Override
    protected void onManagerAdded() {
        super.onManagerAdded();
        getManager().addService(new SubService());
    }

    @MethodVisibility(visibility = VisibilityType.PUBLIC)
    public ReturnableObject<?> method(EventObject eventObject) throws Exception {
        eventObject.getSession().destroy();
        ReturnableObject<?> returnableObject = propagate(eventObject.subEvent("subService:method1",40,2));
        return null;
    }
}

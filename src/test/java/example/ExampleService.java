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
    public ReturnableObject<?> method() throws Exception {
        ReturnableObject<?> returnableObject = propagate(new EventObject("subService:method1", 40,2));
        return null;
    }
}

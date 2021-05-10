package example;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.ExtendableService;
import bombe2.core.data.ReturnableObject;
import bombe2.distributed.SessionProvider;

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
    public ReturnableObject<?> method(SessionProvider eventObject) throws Exception {
        System.out.println(eventObject);
        return null;
    }
}

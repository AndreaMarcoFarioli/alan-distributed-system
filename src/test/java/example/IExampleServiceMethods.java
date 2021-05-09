package example;

import bombe2.annotations.MethodVisibility;
import bombe2.annotations.VisibilityType;
import bombe2.core.data.EventObject;
import bombe2.core.data.ReturnableObject;

public interface IExampleServiceMethods {
    ReturnableObject<?> method(EventObject eventObject) throws Exception;
}

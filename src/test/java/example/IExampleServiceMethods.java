package example;

import bombe2.core.data.ReturnableObject;
import bombe2.distributed.SessionProvider;

public interface IExampleServiceMethods {
    ReturnableObject<?> method(SessionProvider eventObject) throws Exception;
}

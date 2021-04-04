package bombe.distributedArchitecture;

import bombe.core.data.EventObject;
import bombe.core.data.ReturnableObject;
import java.rmi.Remote;

public interface RemoteNode extends Remote {
    ReturnableObject<?> call(EventObject eventObject) throws Exception;
}

package example;

import bombe2.core.data.EventObject;
import bombe2.distributed.NodeProvider;
import bombe2.distributed.RemoteNode;

import java.rmi.RemoteException;

public class SimpleNodeProvider implements NodeProvider {
    @Override
    public RemoteNode getNode() throws RemoteException {
        return null;
    }

    @Override
    @Deprecated
    public EventObject middleware(EventObject eventObject) {
        String fork = eventObject.getFork();
        return switch (fork){
            case "master" -> eventObject;
            default -> redirectEncapsulation(eventObject);
        };
    }

    private EventObject redirectEncapsulation(EventObject eventObject){
        return new EventObject("redirect:bridge", eventObject);
    }
}

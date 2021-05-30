package bombe2.distributed;

import bombe2.annotations.Origin;
import bombe2.core.data.EventObject;
import bombe2.core.Manager;
import bombe2.core.data.ReturnableObject;
import bombe2.core.definitions.HasManager;
import bombe2.exceptions.MalformedEventException;
import java.lang.reflect.Field;
import java.rmi.RemoteException;

public final class RootManager extends UnicastRemoteObject implements RemoteNode, HasManager {
    private final Manager manager = new Manager();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private NodeProvider nodeProvider;
=======
    private InterComChannel interComUnit;
>>>>>>> Stashed changes
=======
    private InterComChannel interComUnit;
>>>>>>> Stashed changes
    private static RootManager instance = null;

    private RootManager() throws RemoteException {
    }

    public static RootManager getInstance(){
        if (instance == null) {
            try {
                instance = new RootManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
    public void init(InterComChannel interComUnit){
        if (this.interComUnit == null)
            this.interComUnit = interComUnit;
    }

>>>>>>> Stashed changes
    /**
     *
     * @param eventObject
     * @return
     * @throws Exception
     */
    @Override
    public ReturnableObject<?> call(EventObject eventObject) throws ReflectiveOperationException, RemoteException {
        ReturnableObject<?> returnableObject;
        Field field = EventObject.class.getDeclaredField("origin");
        field.setAccessible(true);
        field.set(eventObject, Origin.REMOTE);
        field.setAccessible(false);
        returnableObject = manager.propagate(eventObject);
        return returnableObject;
    }

    public void setNodeProvider(NodeProvider nodeProvider) {
        this.nodeProvider = nodeProvider;
    }

    public ReturnableObject<?> sendOver(EventObject eventObject)
            throws ReflectiveOperationException, RemoteException, MalformedEventException {
        ReturnableObject<?> returnableObject = null;
        returnableObject = nodeProvider.getNode().call(nodeProvider.middleware(eventObject));
        return returnableObject;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
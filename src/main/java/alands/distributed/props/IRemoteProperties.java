package alands.distributed.props;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

public interface IRemoteProperties extends Remote {
    Set<String> getKeySet() throws RemoteException;

    Collection<String> getValues() throws RemoteException;

    String getString(String key) throws RemoteException;

    Integer getInt(String key) throws RemoteException;

    Boolean getBool(String key) throws RemoteException;

    String setProperty(String key, String value) throws RemoteException;

    String deleteProperty(String key) throws RemoteException;
}

package alands.distributed.props;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Set;

public class RemoteProperties extends UnicastRemoteObject implements IRemoteProperties {
    private final Properties properties;

    protected RemoteProperties(Properties properties) throws RemoteException {
        this.properties = properties;
    }

    @Override
    public Set<String> getKeySet() throws RemoteException {
        return properties.getKeySet();
    }

    @Override
    public Collection<String> getValues() throws RemoteException {
        return properties.getValues();
    }

    @Override
    public String getString(String key) throws RemoteException {
        return properties.getString(key);
    }

    @Override
    public Integer getInt(String key) throws RemoteException {
        return properties.getInt(key);
    }

    @Override
    public Boolean getBool(String key) throws RemoteException {
        return properties.getBool(key);
    }

    @Override
    public String setProperty(String key, String value) throws RemoteException {
        return properties.setProperty(key, value);
    }

    @Override
    public String deleteProperty(String key) throws RemoteException {
        return properties.deleteProperty(key);
    }
}

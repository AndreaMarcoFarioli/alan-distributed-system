package alands.distributed.props;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Set;

public class ReferencedProperties implements Properties {
    private final IRemoteProperties remoteProperties;

    public ReferencedProperties(IRemoteProperties remoteProperties){
        this.remoteProperties = remoteProperties;
    }

    @Override
    public Set<String> getKeySet() {
        Set<String> keySet = null;
        try {
            keySet = remoteProperties.getKeySet();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return keySet;
    }

    @Override
    public Collection<String> getValues() {
        Collection<String> values = null;
        try {
            values = remoteProperties.getValues();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return values;
    }

    @Override
    public String getString(String key) {
        String stringProperty = null;
        try {
            stringProperty = remoteProperties.getString(key);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return stringProperty;
    }

    @Override
    public int getInt(String key) {
        int intProperty = 0;
        try {
            intProperty = remoteProperties.getInt(key);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return intProperty;
    }

    @Override
    public boolean getBool(String key) {
        boolean booleanProperty = false;
        try {
            booleanProperty = remoteProperties.getBool(key);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return booleanProperty;
    }

    @Override
    public String setProperty(String key, String value) {
        String prevValue = null;
        try {
            prevValue = remoteProperties.setProperty(key, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return prevValue;
    }

    @Override
    public String deleteProperty(String key) {
        String prevValue = null;
        try {
            prevValue = remoteProperties.deleteProperty(key);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return prevValue;
    }

    @Override
    public ReferencedProperties clone() {
        return this;
    }
}

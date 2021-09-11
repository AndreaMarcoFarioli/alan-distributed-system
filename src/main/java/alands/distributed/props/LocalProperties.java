package alands.distributed.props;

import java.io.*;
import java.util.Collection;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LocalProperties implements Properties {
    private final Map<String, String> propertyMap;

    public LocalProperties(){
        this(null);
    }

    public LocalProperties(Map<String, String> propertyMap){
        this.propertyMap = propertyMap == null ? new ConcurrentHashMap<>() : propertyMap;
    }

    public static LocalProperties loadFromStreamReader(BufferedReader bufferedReader)
            throws IOException {
        LocalProperties properties = new LocalProperties();
        Properties.addAll(properties, bufferedReader);
        return properties;
    }

    public static LocalProperties loadFromFile(File file)
            throws IOException {
        return loadFromStreamReader(
                new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(file)
                        )));
    }

    @Override
    public Set<String> getKeySet(){
        return propertyMap.keySet();
    }

    @Override
    public Collection<String> getValues(){
        return propertyMap.values();
    }

    @Override
    public String getString(String key){
        return propertyMap.get(key);
    }

    @Override
    public int getInt(String key){
        return Integer.parseInt(propertyMap.get(key));
    }

    @Override
    public boolean getBool(String key){
        return Boolean.parseBoolean(propertyMap.get(key));
    }

    @Override
    public String setProperty(String key, String value){
        return propertyMap.put(key,value);
    }

    @Override
    public String deleteProperty(String key) {
        return propertyMap.remove(key);
    }

    @Override
    public LocalProperties clone() {
        return (LocalProperties) Properties.join(new LocalProperties(), this);
    }
}
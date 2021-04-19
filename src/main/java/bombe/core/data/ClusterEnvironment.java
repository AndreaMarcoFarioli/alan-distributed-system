package bombe.core.data;

import bombe.core.definitions.Env;
import java.util.HashMap;
import java.util.Map;

public class ClusterEnvironment extends Env {
    private static ClusterEnvironment clusterEnvironment;
    private final Map<String, Object> variables = new HashMap<>();

    private ClusterEnvironment(){
        super();
    }

    public static ClusterEnvironment getInstance(){
        if (clusterEnvironment == null)
            clusterEnvironment = new ClusterEnvironment();
        return clusterEnvironment;
    }

    @Override
    public void set(String id, Object data){
        variables.put(id, data);
    }

    @Override
    public Object getObject(String id){
        return variables.get(id);
    }

    @Override
    public String getString(String id){
        return (String) variables.get(id);
    }

    @Override
    public Integer getInteger(String id){
        return (Integer) variables.get(id);
    }
}

package bombe.core.data;

import java.util.HashMap;
import java.util.Map;

public class ClusterEnvironment {
    private static ClusterEnvironment instance;
    private final Map<String, Object> variables = new HashMap<>();

    private ClusterEnvironment(){};
    public static ClusterEnvironment getInstance(){
        if (instance == null)
            instance = new ClusterEnvironment();
        return instance;
    }

    public void set(String id, Object data){
        variables.put(id, data);
    }

    public Object getObject(String id){
        return variables.get(id);
    }

    public String getString(String id){
        return (String) variables.get(id);
    }

    public Integer getInteger(String id){
        return (Integer) variables.get(id);
    }
}

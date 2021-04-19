package bombe.core.definitions;

import bombe.core.data.ClusterEnvironment;

public abstract class Env {
    private static Env instance;
    protected Env(){
        instance = this;
    }
    public static Env getInstance(){
        return instance;
    }
    public abstract void set(String id, Object data);
    public abstract Object getObject(String id);
    public abstract String getString(String id);
    public abstract Integer getInteger(String id);
}

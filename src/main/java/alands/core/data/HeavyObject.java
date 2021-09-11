package alands.core.data;

import java.io.Serializable;

public class HeavyObject <T> implements Serializable {
    private int heavy = (int)(Math.floor(10 * Math.random()))+1;
    private final T object;
    public HeavyObject(T object){
        this.object = object;
    }

    public int getHeavy() {
        return heavy;
    }

    public T getObject() {
        return object;
    }

    public void changeHeavy(int value){
        this.heavy = value;
    }
}

package core;

import java.io.Serializable;

public class ReturnableObject<T> implements Serializable {
    private final T data;
    public ReturnableObject(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ReturnableObject{" +
                "data=" + data +
                '}';
    }
}

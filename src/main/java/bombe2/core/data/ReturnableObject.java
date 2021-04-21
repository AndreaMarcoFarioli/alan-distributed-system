package bombe2.core.data;

import java.io.Serializable;

public final class ReturnableObject<T> implements Serializable {
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

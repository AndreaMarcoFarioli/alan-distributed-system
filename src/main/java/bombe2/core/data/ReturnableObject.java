package bombe2.core.data;

import java.io.Serializable;

/**
 * Classe contenitore, serializzabile, che viene restituita genericamente dalle invocazioni dei metodi tramite eventi
 * @param <T> tipologia utilizzata al fine di convertire i metodi dell'oggetto
 */
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

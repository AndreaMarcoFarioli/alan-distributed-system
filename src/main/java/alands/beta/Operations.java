package alands.beta;

public interface Operations<T extends VeryLongInt> {
    default T sum(T v1, T v2){
        return add(v1, v2);
    }

    default T sub(T v1, T v2){
        return add(v1, v2);
    }

    T add(T v1, T v2);
}

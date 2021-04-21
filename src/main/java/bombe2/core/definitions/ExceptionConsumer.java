package bombe2.core.definitions;

@FunctionalInterface
public interface ExceptionConsumer<T,E extends Exception> {
    void accept(T t) throws E;
}

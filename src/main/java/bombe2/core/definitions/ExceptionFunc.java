package bombe2.core.definitions;

public interface ExceptionFunc<T extends Exception, D> {
    D execute() throws T;
}

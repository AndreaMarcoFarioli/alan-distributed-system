package bombe2.distributedArchitecture;

public interface TransactionsManager {
    void beforeInvocation(Object param);
    void afterInvocation(Object param);
}

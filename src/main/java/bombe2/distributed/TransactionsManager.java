package bombe2.distributed;

public interface TransactionsManager {
    void beforeInvocation(Object param);
    void afterInvocation(Object param);
}

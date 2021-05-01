package bombe2.distributed;

@Deprecated
public interface TransactionsManager {
    void beforeInvocation(Object param);
    void afterInvocation(Object param);
}

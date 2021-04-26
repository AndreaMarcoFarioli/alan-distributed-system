package bombe2.distributed;

import bombe2.core.definitions.ExceptionFunc;

public class ClusterTiming {
    private long
        execution,
        read,
        write;

    private int
        cExecution,
        cRead,
        cWrite;

    public <D> D delegateTransaction(ExceptionFunc<Exception, D> transaction, DataTransactionType dataTransactionType) throws Exception {
        D value;
        DataTransactionTimer dataTransactionTimer = new DataTransactionTimer(dataTransactionType);
        dataTransactionTimer.startTimer();
        value = transaction.execute();
        dataTransactionTimer.endTimer();
        commitTransaction(dataTransactionTimer);
        return value;
    }

    private void commitTransaction(DataTransactionTimer dataTransactionTimer){
        switch (dataTransactionTimer.getDataTransactionType()){
            case READ -> {
                read += readCalc(dataTransactionTimer.getDelta());
                cRead++;
            }
            case WRITE -> {
                write += writeCalc(dataTransactionTimer.getDelta());
                cWrite++;
            }
            case EXECUTION -> {
                execution += executionCalc(dataTransactionTimer.getDelta());
                cExecution++;
            }
        }
    }

    public long readCalc(long delta){
        return delta;
    }

    public long writeCalc(long delta){
        return delta;
    }

    public long executionCalc(long delta){
        return delta;
    }

    @Override
    public String toString() {
        return "ClusterTiming{" +
                "execution=" + execution +
                ", read=" + read +
                ", write=" + write +
                ", cExecution=" + cExecution +
                ", cRead=" + cRead +
                ", cWrite=" + cWrite +
                '}';
    }
}

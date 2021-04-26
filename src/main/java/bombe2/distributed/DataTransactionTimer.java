package bombe2.distributed;

public class DataTransactionTimer {
    private final DataTransactionType dataTransactionType;
    private boolean timerStarted = false;
    private long nanoStart, nanoStop;
    public DataTransactionTimer(DataTransactionType dataTransactionType){
        this.dataTransactionType = dataTransactionType;
    }

    public DataTransactionType getDataTransactionType() {
        return dataTransactionType;
    }

    public boolean isTimerStarted() {
        return timerStarted;
    }

    public void startTimer(){
        if (timerStarted)
            return;

        timerStarted = true;
        nanoStart = System.nanoTime();
    }

    public void endTimer(){
        nanoStop = System.nanoTime();
    }

    public long getDelta(){
        return nanoStop - nanoStart;
    }
}
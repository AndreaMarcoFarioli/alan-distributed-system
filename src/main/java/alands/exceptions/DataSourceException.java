package alands.exceptions;

public class DataSourceException extends RuntimeException{
    public DataSourceException(){}
    public DataSourceException(String msg){
        super(msg);
    }

    public DataSourceException(Throwable throwable){
        super(throwable);
    }
}

package bombe2.exceptions;

public class NoSuchSessionException extends RuntimeException{
    public NoSuchSessionException(String message){
        super(message);
    }

    public NoSuchSessionException(){}
}

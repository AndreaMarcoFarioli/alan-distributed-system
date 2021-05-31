package alands.exceptions;

public class NoSuchSessionException extends RuntimeException{
    public NoSuchSessionException(String message){
        super(message);
    }
    public NoSuchSessionException(){
        super();
    }
}

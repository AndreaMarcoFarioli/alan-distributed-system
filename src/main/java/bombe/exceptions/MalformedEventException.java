package bombe.exceptions;

import java.io.IOException;

public final class MalformedEventException extends RuntimeException {
    public MalformedEventException(String message){
        super(message);
    }

    public MalformedEventException(){

    }
}

package bombe2.exceptions;

import java.io.IOException;

public final class MalformedEventException extends IOException {
    public MalformedEventException(String message){
        super(message);
    }

    public MalformedEventException(){

    }
}

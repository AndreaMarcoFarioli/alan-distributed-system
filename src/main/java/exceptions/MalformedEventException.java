package exceptions;

import java.io.IOException;

public class MalformedEventException extends IOException {
    public MalformedEventException(String message){
        super(message);
    }

    public MalformedEventException(){

    }
}

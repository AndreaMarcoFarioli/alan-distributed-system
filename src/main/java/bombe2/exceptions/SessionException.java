package bombe2.exceptions;

import java.io.IOException;

public class SessionException extends IllegalStateException {
    public SessionException(){}
    public SessionException(String message){super(message);}
}

package cse41321.exceptions;

public class DuplicateKeyException extends Exception { 
    public DuplicateKeyException() {
        super("Duplicate Key Found");
    }
}

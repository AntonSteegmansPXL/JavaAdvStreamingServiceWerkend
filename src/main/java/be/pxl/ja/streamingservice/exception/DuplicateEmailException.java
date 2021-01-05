package be.pxl.ja.streamingservice.exception;

public class DuplicateEmailException extends Exception{

    public DuplicateEmailException() {
        super("Er bestaat al een account met dit emailadres");
    }
}

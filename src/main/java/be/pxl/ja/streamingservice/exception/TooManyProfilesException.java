package be.pxl.ja.streamingservice.exception;

public class TooManyProfilesException extends Exception{
    public TooManyProfilesException() {
        super("Je zit al aan het maximum aantal profielen");
    }
}

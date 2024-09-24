package mk.ukim.finki.emtlabb.model.exceptions;

public class NoMoreRoomsException extends RuntimeException{
    public NoMoreRoomsException() {
        super("No more rooms available");
    }
}

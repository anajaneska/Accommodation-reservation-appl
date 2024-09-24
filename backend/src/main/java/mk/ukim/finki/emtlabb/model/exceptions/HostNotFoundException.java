package mk.ukim.finki.emtlabb.model.exceptions;

public class HostNotFoundException extends RuntimeException{
    public HostNotFoundException(Long id) {
        super(String.format("Host with id: %d is not found", id));
    }
}

package mk.ukim.finki.emtlabb.model.exceptions;

public class NoSuchAccommodationException extends RuntimeException{
    public NoSuchAccommodationException(Long id) {
        super(String.format("Accommodation with id: %d is not found", id));
    }
}

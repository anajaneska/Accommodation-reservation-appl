package mk.ukim.finki.emtlabb.events;

import mk.ukim.finki.emtlabb.model.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationCreatedEvent extends ApplicationEvent {
    public AccommodationCreatedEvent(Accommodation source) {
        super(source);
    }
}

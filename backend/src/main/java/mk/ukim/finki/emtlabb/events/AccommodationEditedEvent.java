package mk.ukim.finki.emtlabb.events;

import mk.ukim.finki.emtlabb.model.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationEditedEvent extends ApplicationEvent {
    public AccommodationEditedEvent(Accommodation source) {
        super(source);
    }
}

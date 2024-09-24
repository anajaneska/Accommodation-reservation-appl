package mk.ukim.finki.emtlabb.events;

import lombok.Getter;
import mk.ukim.finki.emtlabb.model.Accommodation;
import org.springframework.context.ApplicationEvent;


public class AccommodationDeletedEvent extends ApplicationEvent {
    public AccommodationDeletedEvent(Accommodation source) {
        super(source);
    }
}

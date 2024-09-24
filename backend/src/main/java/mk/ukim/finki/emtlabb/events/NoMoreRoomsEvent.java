package mk.ukim.finki.emtlabb.events;

import org.springframework.context.ApplicationEvent;

public class NoMoreRoomsEvent extends ApplicationEvent {
    public NoMoreRoomsEvent(Object source) {
        super(source);
    }
}

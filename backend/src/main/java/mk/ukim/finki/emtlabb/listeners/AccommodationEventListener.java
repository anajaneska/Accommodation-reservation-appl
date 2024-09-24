package mk.ukim.finki.emtlabb.listeners;

import mk.ukim.finki.emtlabb.events.AccommodationCreatedEvent;
import mk.ukim.finki.emtlabb.events.AccommodationDeletedEvent;
import mk.ukim.finki.emtlabb.events.AccommodationEditedEvent;
import mk.ukim.finki.emtlabb.events.NoMoreRoomsEvent;
import mk.ukim.finki.emtlabb.service.AccommodationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccommodationEventListener {
    private final AccommodationService accommodationService;

    public AccommodationEventListener(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    @EventListener
    public void onAccommodationCreated(AccommodationCreatedEvent event){
        this.accommodationService.onAccommodationCreated();
    }
    @EventListener
    public void onAccommodationEdited(AccommodationEditedEvent event){
        this.accommodationService.onAccommodationEdited();
    }
    @EventListener
    public void onAccommodationDeleted(AccommodationDeletedEvent event){
        this.accommodationService.onAccommodationDeleted();
    }
    @EventListener
    public void onNoMoreRooms(NoMoreRoomsEvent event){
        this.accommodationService.onNoMoreRooms();
    }

}

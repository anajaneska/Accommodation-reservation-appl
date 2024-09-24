package mk.ukim.finki.emtlabb.service;

import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.dto.AccommodationDto;
import mk.ukim.finki.emtlabb.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAllAccommodations();
    Optional<Accommodation> findAccommodationById(Long id);
    Optional<Accommodation> saveAccommodation(String name, Category category, Long hostId, Integer numRooms);
    Optional<Accommodation> saveAccommodation(AccommodationDto accommodationDto);
    Optional<Accommodation> editAccommodation(Long id, String name, Category category, Long hostId, Integer numRooms);
    Optional<Accommodation> editAccommodation(Long id, AccommodationDto accommodationDto);
    void deleteAccommodation(Long id);
    void changeRentedStatus(Long id);

    void onAccommodationCreated();
    void onAccommodationEdited();
    void onAccommodationDeleted();
    void onNoMoreRooms();
    void rentRoomFromAccommodation(Long id);
}

package mk.ukim.finki.emtlabb.service.impl;


import mk.ukim.finki.emtlabb.events.AccommodationDeletedEvent;
import mk.ukim.finki.emtlabb.events.AccommodationEditedEvent;
import mk.ukim.finki.emtlabb.events.NoMoreRoomsEvent;
import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.model.dto.AccommodationDto;
import mk.ukim.finki.emtlabb.model.enumerations.Category;
import mk.ukim.finki.emtlabb.model.exceptions.HostNotFoundException;
import mk.ukim.finki.emtlabb.model.exceptions.NoMoreRoomsException;
import mk.ukim.finki.emtlabb.model.exceptions.NoSuchAccommodationException;
import mk.ukim.finki.emtlabb.repository.AccommodationRepository;
import mk.ukim.finki.emtlabb.repository.HostRepository;
import mk.ukim.finki.emtlabb.service.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Accommodation> listAllAccommodations() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findAccommodationById(Long id)  {
        return this.accommodationRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Accommodation> saveAccommodation(String name, Category category, Long hostId, Integer numRooms) {
        Host host  = this.hostRepository.findById(hostId)
                .orElseThrow(()->new HostNotFoundException(hostId));

        Accommodation accommodation = new Accommodation(name,category,host,numRooms);
        this.accommodationRepository.save(accommodation);

        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> saveAccommodation(AccommodationDto accommodationDto) {
        Host host  = this.hostRepository.findById(accommodationDto.getHost())
                .orElseThrow(()->new HostNotFoundException(accommodationDto.getHost()));

        Accommodation accommodation = new Accommodation(accommodationDto.getName(),accommodationDto.getCategory(),host, accommodationDto.getNumRooms());

        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    @Transactional
    public Optional<Accommodation> editAccommodation(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        Host host  = this.hostRepository.findById(hostId)
                .orElseThrow(()->new HostNotFoundException(hostId));

        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setNumRooms(numRooms);
        accommodation.setHost(host);

        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> editAccommodation(Long id, AccommodationDto accommodationDto) {
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        Host host  = this.hostRepository.findById(accommodationDto.getHost())
                .orElseThrow(()->new HostNotFoundException(accommodationDto.getHost()));
        accommodation.setName(accommodation.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setHost(host);
        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public void deleteAccommodation(Long id) {
        Accommodation acc = this.accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        this.applicationEventPublisher.publishEvent(new AccommodationDeletedEvent(acc));
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public void changeRentedStatus(Long id) {
        Accommodation accommodation=this.accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        accommodation.setCurrentlyRented(!accommodation.isCurrentlyRented());
        accommodation.setNumRooms(0);
        this.accommodationRepository.save(accommodation);
    }

    @Override
    public void onAccommodationCreated() {
        System.out.println("[CREATE]: Accommodation created successfully");
    }

    @Override
    public void onAccommodationEdited() {
        System.out.println("[EDIT]: Accommodation edited successfully");
    }

    @Override
    public void onAccommodationDeleted() {
        System.out.println("[DELETE]: Accommodation delete successfully");
    }

    @Override
    public void onNoMoreRooms() {
        System.out.println("NO MORE ROOMS FOR THIS ACCOMMODATION");
    }

    @Override
    public void rentRoomFromAccommodation(Long id) {
        Accommodation acc = accommodationRepository.findById(id)
                .orElseThrow(()->new NoSuchAccommodationException(id));
        if(acc.getNumRooms()-1>=0){
            acc.setNumRooms(acc.getNumRooms()-1);
            if(acc.getNumRooms()==0){
                this.applicationEventPublisher.publishEvent(new NoMoreRoomsEvent(acc));
            }
            this.accommodationRepository.save(acc);
        }
    }
}

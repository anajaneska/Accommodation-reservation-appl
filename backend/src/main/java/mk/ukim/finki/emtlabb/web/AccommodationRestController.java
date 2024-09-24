package mk.ukim.finki.emtlabb.web;

import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.dto.AccommodationDto;
import mk.ukim.finki.emtlabb.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/accommodations")
public class AccommodationRestController {
    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    @GetMapping
    private List<Accommodation> findAll(){
        return this.accommodationService.listAllAccommodations();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id){
        return this.accommodationService.findAccommodationById(id)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodationDto){
        return this.accommodationService.saveAccommodation(accommodationDto)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> save(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto){
        return this.accommodationService.editAccommodation(id,accommodationDto)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.accommodationService.deleteAccommodation(id);
        if(this.accommodationService.findAccommodationById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/toggle-rented/{id}")
    public ResponseEntity<Void> toggleRentedAccommodation(@PathVariable Long id){
        if(id == null|| accommodationService.findAccommodationById(id) == null){
            return ResponseEntity.notFound().build();
        }
        this.accommodationService.changeRentedStatus(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/rent-room/{id}")
    public ResponseEntity<Void> rentRoomFromAccommodation(@PathVariable Long id){
        if(id == null|| accommodationService.findAccommodationById(id) == null){
            return ResponseEntity.notFound().build();
        }
        this.accommodationService.rentRoomFromAccommodation(id);
        return ResponseEntity.ok().build();
    }


}

package mk.ukim.finki.emtlabb.web;

import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.Country;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping
    private List<Country> findAll(){
        return this.countryService.listAllCountries();
    }
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestParam String name,
                                     @RequestParam String continent) {
        return this.countryService.saveCountry(name, continent)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

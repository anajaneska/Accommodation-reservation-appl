package mk.ukim.finki.emtlabb.service;

import mk.ukim.finki.emtlabb.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAllCountries();
    Optional<Country> getCountryById(Long id);
    Optional<Country> saveCountry(String name, String continent);
    void deleteCountry(Long id);

}

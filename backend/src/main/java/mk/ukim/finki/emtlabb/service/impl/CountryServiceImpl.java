package mk.ukim.finki.emtlabb.service.impl;

import mk.ukim.finki.emtlabb.model.Country;
import mk.ukim.finki.emtlabb.repository.CountryRepository;
import mk.ukim.finki.emtlabb.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> saveCountry(String name, String continent) {
        Country country = new Country(name,continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteCountry(Long id) {
        this.countryRepository.deleteById(id);
    }
}

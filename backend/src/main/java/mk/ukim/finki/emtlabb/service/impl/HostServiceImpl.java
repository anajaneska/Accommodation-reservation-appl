package mk.ukim.finki.emtlabb.service.impl;

import mk.ukim.finki.emtlabb.model.Country;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emtlabb.repository.CountryRepository;
import mk.ukim.finki.emtlabb.repository.HostRepository;
import mk.ukim.finki.emtlabb.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> findAllHosts() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> getHostById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> saveHost(String name, String surname, Long countryId) {
        Country country=this.countryRepository.findById(countryId)
                .orElseThrow(()->new CountryNotFoundException(countryId));
        Host host = new Host(name,surname,country);
        this.hostRepository.save(host);
        return Optional.of(host);
    }

    @Override
    public void deleteHost(Long id) {
        this.hostRepository.deleteById(id);
    }
}

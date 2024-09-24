package mk.ukim.finki.emtlabb.service;

import mk.ukim.finki.emtlabb.model.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAllHosts();
    Optional<Host> getHostById(Long id);
    Optional<Host> saveHost(String name, String surname, Long countryId);
    void deleteHost(Long id);
}

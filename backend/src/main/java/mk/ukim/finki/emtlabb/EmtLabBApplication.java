package mk.ukim.finki.emtlabb;

import mk.ukim.finki.emtlabb.model.Accommodation;
import mk.ukim.finki.emtlabb.model.Country;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.repository.AccommodationRepository;
import mk.ukim.finki.emtlabb.repository.CountryRepository;
import mk.ukim.finki.emtlabb.repository.HostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import static mk.ukim.finki.emtlabb.model.enumerations.Category.*;

@SpringBootApplication
@ServletComponentScan
public class EmtLabBApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmtLabBApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository){
        Country Macedonia = new Country("Macedonia","Europe");
        Country Italy = new Country("Italy","Europe");
        Country Japan = new Country("Japan","Asia");

        Host host1 = new Host("Ana","Janeska",Macedonia);
        Host host2 = new Host("Petar","Petkovski",Italy);
        Host host3 = new Host("Mila", "Milkovska", Japan);

        Accommodation accommodation1 = new Accommodation("Villa 1",APARTMENT,host1,4);
        Accommodation accommodation2 = new Accommodation("Flat",FLAT,host2,3);
        Accommodation accommodation3 = new Accommodation("Cozy House",HOUSE,host3,7);
        Accommodation accommodation4 = new Accommodation("Flat2",FLAT,host2,1);

        return args -> {
            countryRepository.save(Macedonia);
            countryRepository.save(Italy);
            countryRepository.save(Japan);

            hostRepository.save(host1);
            hostRepository.save(host2);
            hostRepository.save(host3);

            accommodationRepository.save(accommodation1);
            accommodationRepository.save(accommodation2);
            accommodationRepository.save(accommodation3);
            accommodationRepository.save(accommodation4);
        };
    }

}

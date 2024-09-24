package mk.ukim.finki.emtlabb.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.model.enumerations.Category;

@Data
public class AccommodationDto {
    private String name;
    private Category category;
    private Long host;
    private Integer numRooms;

    public AccommodationDto() {
    }

    public AccommodationDto(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}

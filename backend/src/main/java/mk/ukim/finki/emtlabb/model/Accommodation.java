package mk.ukim.finki.emtlabb.model;


import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emtlabb.model.enumerations.Category;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;
    private boolean isCurrentlyRented;

    public Accommodation() {
    }

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isCurrentlyRented=false;
    }
}

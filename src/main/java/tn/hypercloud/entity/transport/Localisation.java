package tn.hypercloud.entity.transport;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "localisations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_localisation", updatable = false, nullable = false)
    private UUID idLocalisation;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(length = 500)
    private String adresse;

    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}

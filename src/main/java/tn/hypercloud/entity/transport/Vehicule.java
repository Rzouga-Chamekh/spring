package tn.hypercloud.entity.transport;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vehicules")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_vehicule", updatable = false, nullable = false)
    private UUID idVehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chauffeur", nullable = false)
    private Chauffeur chauffeur;

    @Column(length = 100)
    private String marque;

    @Column(length = 100)
    private String modele;

    @Column(name = "numero_plaque", nullable = false, unique = true, length = 20)
    private String numeroPlaque;

    @Column(name = "type_vehicule", length = 50)
    private String typeVehicule;

    @Column(name = "capacite_passagers")
    private Integer capacitePassagers;

    @Column(name = "prix_km", precision = 10, scale = 2)
    private BigDecimal prixKm;

    @Column(name = "prix_minute", precision = 10, scale = 2)
    private BigDecimal prixMinute;

    @Column(length = 50)
    private String statut;

    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = LocalDateTime.now();
    }
}

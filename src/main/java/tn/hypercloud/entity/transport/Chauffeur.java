package tn.hypercloud.entity.transport;


import tn.hypercloud.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chauffeurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Chauffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_chauffeur", updatable = false, nullable = false)
    private UUID idChauffeur;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false, unique = true)
    private User utilisateur;

    @Column(nullable = false, length = 20)
    private String telephone;

    @Column(name = "numero_licence", nullable = false, unique = true, length = 50)
    private String numeroLicence;

    @Column(length = 50)
    private String statut;

    @Column(name = "note_moyenne", precision = 3, scale = 2)
    private BigDecimal noteMoyenne;

    @Column(length = 10)
    private String disponibilite;

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

package tn.hypercloud.entity.transport;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "annulations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Annulation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_annulation", updatable = false, nullable = false)
    private UUID idAnnulation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", nullable = false, unique = true)
    private Course course;

    @Column(name = "annule_par", length = 50)
    private String annulePar;

    @Column(length = 500)
    private String raison;

    @Column(name = "montant_penalite", precision = 10, scale = 2)
    private BigDecimal montantPenalite;

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

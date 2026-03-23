package tn.hypercloud.entity.transport;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "paiements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_paiement", updatable = false, nullable = false)
    private UUID idPaiement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", nullable = false, unique = true)
    private Course course;

    @Column(precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(length = 50)
    private String methode;

    @Column(length = 50)
    private String statut;

    @Column(name = "date_paiement")
    private LocalDateTime datePaiement;

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

package tn.hypercloud.entity.transport;


import tn.hypercloud.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_course", updatable = false, nullable = false)
    private UUID idCourse;

    /** Le client (role = CLIENT_TOURISTE) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chauffeur")
    private Chauffeur chauffeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicule")
    private Vehicule vehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localisation_depart", nullable = false)
    private Localisation localisationDepart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localisation_arrivee", nullable = false)
    private Localisation localisationArrivee;

    @Column(length = 50)
    private String statut;

    @Column(name = "prix_estime", precision = 10, scale = 2)
    private BigDecimal prixEstime;

    @Column(name = "prix_final", precision = 10, scale = 2)
    private BigDecimal prixFinal;

    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Paiement paiement;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Evaluation evaluation;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Annulation annulation;

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

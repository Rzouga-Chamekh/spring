package tn.hypercloud.entity.transport;


import tn.hypercloud.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "evaluations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_evaluation", updatable = false, nullable = false)
    private UUID idEvaluation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", nullable = false, unique = true)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluateur", nullable = false)
    private User evaluateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evalue", nullable = false)
    private User evalue;

    private Integer note;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

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

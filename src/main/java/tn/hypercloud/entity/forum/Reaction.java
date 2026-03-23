package tn.hypercloud.entity.forum;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reaction",
    indexes = {
        @Index(name = "idx_forum_id", columnList = "forum_id")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Reaction type (e.g. LIKE, DISLIKE) */
    @Column(length = 50)
    private String type;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

package tn.hypercloud.entity.forum;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review",
    indexes = {
        @Index(name = "idx_forum_id", columnList = "forum_id")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    /** Whether the review was flagged by AI moderation */
    @Column(name = "flaggedByAI")
    private Boolean flaggedByAI;

    @Column(length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        flaggedByAI = false;
    }
}

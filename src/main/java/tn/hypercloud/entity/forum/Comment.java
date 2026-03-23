package tn.hypercloud.entity.forum;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment",
    indexes = {
        @Index(name = "idx_forum_id", columnList = "forum_id")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    /** Whether the comment was flagged by AI moderation */
    @Column(name = "flaggedByAI")
    private Boolean flaggedByAI;

    @Column(name = "aiStatus", length = 50)
    private String aiStatus;

    @Column(name = "aiReason", length = 255)
    private String aiReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    private Forum forum;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        flaggedByAI = false;
    }
}

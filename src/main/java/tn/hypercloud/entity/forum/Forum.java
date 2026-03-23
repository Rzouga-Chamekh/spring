package tn.hypercloud.entity.forum;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "forum",
    indexes = {
        @Index(name = "idx_community_id", columnList = "community_id")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(length = 50)
    private String status;

    @Column(name = "likesCount")
    private Integer likesCount;

    @Column(name = "dislikesCount")
    private Integer dislikesCount;

    private Integer views;

    /** Whether the post was flagged by AI moderation */
    @Column(name = "flaggedByAI")
    private Boolean flaggedByAI;

    /** AI toxicity score (0.0 - 1.0) */
    @Column(name = "toxicityScore")
    private Double toxicityScore;

    @Column(name = "aiDecision", length = 255)
    private String aiDecision;

    @Column(name = "containsForbiddenWords")
    private Boolean containsForbiddenWords;

    @Column(name = "aiStatus", length = 50)
    private String aiStatus;

    @Column(name = "aiReason", length = 255)
    private String aiReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        likesCount = 0;
        dislikesCount = 0;
        views = 0;
        flaggedByAI = false;
        containsForbiddenWords = false;
    }
}

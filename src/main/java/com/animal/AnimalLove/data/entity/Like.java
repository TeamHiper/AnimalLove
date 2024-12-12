package com.animal.AnimalLove.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "like")
@NoArgsConstructor
public class Like extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @org.hibernate.annotations.Comment(value = "유저 아이디")
    @ToString.Exclude
    private User user;

    private Long postId;

    @Builder
    public Like(Long likeId, User user, Long postId) {
        this.likeId = likeId;
        this.user = user;
        this.postId = postId;
    }

}

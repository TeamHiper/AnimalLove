package com.animal.AnimalLove.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "image")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment(value = "경로")
    private String url;

    @Comment(value = "퍼블릭 아이디")
    private String publicId; // Cloudinary에서 제공하는 public_id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    @Comment(value = "포스트 아이디")
    private Post post;

    @Builder
    public Image(String url, String publicId, Post post){
        this.url=url;
        this.publicId=publicId;
        this.post=post;
    }

}

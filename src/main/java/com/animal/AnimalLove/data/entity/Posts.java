package com.animal.AnimalLove.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "posts")
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "포스트 아이디")
    private Long postId;

    @Column(nullable = false)
    @Comment(value = "내용")
    private String content;

    @Column(nullable = false)
    @Comment(value = "이미지")
    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @Comment(value = "유저 아이디")
    @ToString.Exclude
    private Users user;

}

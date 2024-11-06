package com.animal.AnimalLove.data.entity;

import com.animal.AnimalLove.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.Comment(value = "포스트 아이디")
    private Long postId;

    @Column(nullable = false)
    @org.hibernate.annotations.Comment(value = "내용")
    private String content;

    // test용
    @Column(nullable = true)
    @org.hibernate.annotations.Comment(value = "이미지")
    @Lob
    private byte[] image;

    @Convert(converter = StringListConverter.class)
    private List<String> tags; // 게시글에 사용된 태그 목록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @org.hibernate.annotations.Comment(value = "유저 아이디")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> Comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Image> images;

    @Builder
    public Post(Long postId, String content, byte[] image, User user) {
        this.postId = postId;
        this.content = content;
        this.image = image;
        this.user = user;
    }

}

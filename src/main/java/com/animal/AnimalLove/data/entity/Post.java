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

    @Convert(converter = StringListConverter.class)
    private List<String> tags; // 게시글에 사용된 태그 목록

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @org.hibernate.annotations.Comment(value = "유저 아이디")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> Comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Image> images = new ArrayList<>();

    @Builder
    public Post(Long postId, String content, User user) {
        this.postId = postId;
        this.content = content;
        this.user = user;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}

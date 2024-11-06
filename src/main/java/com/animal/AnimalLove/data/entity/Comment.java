package com.animal.AnimalLove.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.Comment(value = "코멘트 아이디")
    private Long commentId;

    @Column(nullable = false)
    @org.hibernate.annotations.Comment(value = "내용")
    private String content;

    @Column(nullable = true)
    @org.hibernate.annotations.Comment(value = "부모 댓글 아이디")
    private String partnerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    @org.hibernate.annotations.Comment(value = "포스트 아이디")
    @ToString.Exclude
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @org.hibernate.annotations.Comment(value = "유저 아이디")
    @ToString.Exclude
    private User user;
}

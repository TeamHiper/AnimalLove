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
@Table(name = "comments")
public class Comments extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "코멘트 아이디")
    private Long commentId;

    @Column(nullable = false)
    @Comment(value = "내용")
    private String content;

    @Column(nullable = false)
    @Comment(value = "부모 댓글 아이디")
    private String partnerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    @Comment(value = "포스트 아이디")
    @ToString.Exclude
    private Posts post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @Comment(value = "유저 아이디")
    @ToString.Exclude
    private Users user;
}

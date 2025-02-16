package com.animal.AnimalLove.data.entity;

import com.animal.AnimalLove.converter.LongListConverter;
import com.animal.AnimalLove.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "유저 아이디")
    private Long userId;

    @Column(nullable = false)
    @Comment(value = "닉네임")
    private String nickname;

    @Column(nullable = false)
    @Comment(value = "oauth2 프로바이더Id")
    private String provider;

    @Comment(value = "유저 실명")
    private String name;

    @Column(nullable = false)
    @Comment(value = "이메일")
    private String email;

    @Column(nullable = false)
    @Comment(value = "역할")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Like> likes;

    @Column(nullable = true)
    @Comment(value = "프로필 이미지 경로")
    private String profileImageUrl;

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Builder
    public User(Long userId,String provider,String nickname,String name, String email, String role, String profileImageUrl) {
        this.userId = userId;
        this.provider = provider;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
    }

}

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
    @Comment(value = "유저 이름")
    private String username;

    @Comment(value = "유저 실명")
    private String name;

    @Column(nullable = false)
    @Comment(value = "이메일")
    private String email;

    @Column(nullable = false)
    @Comment(value = "역할")
    private String role;

    // test용
    @Column(nullable = true)
    @Comment(value = "프로필 이미지")
    @Lob
    private byte[] profileImage;


    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    private List<Post> posts;

    @Builder
    public User(Long userId, String username,String name, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
    }

}

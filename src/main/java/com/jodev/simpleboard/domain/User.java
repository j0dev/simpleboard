package com.jodev.simpleboard.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;    // user pk

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;   // user email (use as id when login)

    @NotBlank
    @Size(max = 50)
    private String name;    // user name

    @NotBlank
    @Size(max = 512)
    private String password;    // user password (sha512 encrypted data)

    @Enumerated(EnumType.STRING)
    private RoleType role;      // user role type (enum : USER, ADMIN)

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();    // user created time

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();    // user updated time

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<Board> board;      // user writen board

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<Comment> comment;      // user writen comment


    @Builder
    public User(String email, String name, String password, RoleType role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

}

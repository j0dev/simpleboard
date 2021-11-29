package com.jodev.simpleboard.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;    // pk

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;      // writer

    @Enumerated(EnumType.STRING)
    BoardType boardType;        // board type (enum: PUBLIC, PRIVATE)

    @NotBlank
    @Size(max = 128)
    private String title;   // title

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;     // board's content

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();    // board created time

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now(); // board updated time

    @OneToMany(mappedBy = "board", fetch = LAZY)
    private List<Comment> comment;

    @Builder
    public Board(User user, String title, String content, BoardType boardType) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
    }

}

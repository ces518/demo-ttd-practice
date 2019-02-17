package me.june.demottdpractice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "board")
@Getter @Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id @GeneratedValue
    private Long seq;

    @Column(name = "title",length = 1000)
    private String title;

    @Column(name = "content", length = 4000)
    private String content;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDate modifiedAt;

    @Column(name = "writer", length = 200)
    private String writer;


    @Builder
    public Board(String title, String content, LocalDate createdAt, LocalDate modifiedAt, String writer) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.writer = writer;
    }
}

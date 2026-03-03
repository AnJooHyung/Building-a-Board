package com.board.defalut.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@SQLRestriction("is_deleted = false")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long post_id;

    @Column(nullable = false)
    private String title;

    private String content;

    private LocalDateTime created_time;

    private boolean isDeleted = false;

    // 업데이트 기능 수행시 사용되는 캡슐화
    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
    // 삭제 기능 수행시 사용되는 캡슐화
    public void markAsDeleted() {
        this.isDeleted = true;
    }

}

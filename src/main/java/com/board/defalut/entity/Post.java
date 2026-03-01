package com.board.defalut.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long post_id;

    @Column(nullable = false)
    private String title;

    private String content;

}

package com.kor.challenger.domain.dto;

import com.kor.challenger.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class PostsResponseDto {
    private List<Post> posts;
}

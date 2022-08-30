package com.example.demo.dto.retro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO1 {
    private String content;
    private Long user_id;
    private Long discussion_id;
    private String displayName;


}

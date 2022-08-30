package com.example.demo.dto.retro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Collection;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDTO1 {
    private Long id;
    private String content;
    private Date createDate;
    private Long boardColunm_id;
    private Long user_id;
    private Long numberVote;
    private Long numberUnvote;
    private String displayName;
}

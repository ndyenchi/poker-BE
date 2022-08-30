package com.example.demo.repository.boardRetrospective;

import com.example.demo.model.boardRetrospective.BoardRetrospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardRetroReponsitory extends JpaRepository<BoardRetrospective,Long> {
    public BoardRetrospective findByUrl(String url);

    public List<BoardRetrospective> findByUser_id(Long user_id);
}
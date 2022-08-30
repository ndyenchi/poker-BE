package com.example.demo.repository.boardRetrospective;

import com.example.demo.model.boardRetrospective.BoardColunm;
import com.example.demo.model.boardRetrospective.BoardRetrospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardColunmReponsitory extends JpaRepository<BoardColunm,Long> {

}
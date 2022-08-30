package com.example.demo.repository.pokerGame;

import com.example.demo.model.pokerGame.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

  List<History> findByGame_Id(long id);
  List<History> findByGame_Url(String url);

}
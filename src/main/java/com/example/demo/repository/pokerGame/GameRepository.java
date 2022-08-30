package com.example.demo.repository.pokerGame;

import com.example.demo.model.pokerGame.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByUrl(String url);


    @Query(value = "select * from game  where url=:url", nativeQuery = true)
    Game getByUrl(@Param("url") String url);
//    Game findGameById(Long id);
}
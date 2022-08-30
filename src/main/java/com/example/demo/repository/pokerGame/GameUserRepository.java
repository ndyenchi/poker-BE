package com.example.demo.repository.pokerGame;

import com.example.demo.model.pokerGame.GameUser;
import com.example.demo.model.pokerGame.GameUserID;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, GameUserID> {
    public List<GameUser> findByGame_Id(Long id);

//    List<GameUser> findByIdUser_id(Long id);

    // public List<GameUser> findByIdUser_id(Long idUser);
    List<GameUser> findByGameUserID_IdUser(Long idUser);

    @Query("Select g from GameUser g where g.game.url = :url and g.user.id = :userId")
    Optional<GameUser> findByGameUrlAndUserID(@Param("url") String url, @Param("userId") Long userId);

    public List<GameUser> findByGame_Url(String url);

    @Query("Select g from GameUser g where g.game.url = :url and g.flipCard= true ")
    List<GameUser> findGameUserHaveFlipTrue(@Param("url") String url);

    @Query("Select g from GameUser g where g.game.url = :url ")
    List<GameUser> findListGameUserByUrl(@Param("url") String url);

    //    @Query("Select g from GameUser g where g.game.url = :url and g.reveal= true ")
//    GameUser findGameUserRevealByGameId(@Param("url") String url);

    @Query(value = "select avg(point) from GameUser where id_game=:id_game ")
    public Long avg(@Param("id_game") Long id_game);

    @Query(value = " select count(id_user) as players_total from GameUser gu where id_game =:id_game")
    public Long playersTotal(@Param("id_game") Long id_game);

    @Query(value = "  select count(point) as play_voted from GameUser gu where id_game =:id_game")
    public Long playersVote(@Param("id_game") Long id_game);

    Boolean existsByGameUserIDIdUser(long idUser);

    Boolean existsByGameUserIDIdUserAndGame_Url(long idUser, String url);

}
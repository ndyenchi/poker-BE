package com.example.demo.services.RetrospectiveGame;

import com.example.demo.dto.BoardRetrospectiveDTO;
import com.example.demo.dto.retro.DiscussionDTO1;
import com.example.demo.model.boardRetrospective.BoardColunm;
import com.example.demo.model.boardRetrospective.BoardRetrospective;
import com.example.demo.model.boardRetrospective.Discussion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardRetroService {

    List<BoardRetrospective>findAllBoardByUserId(Long user_id);
    public BoardRetrospective findBoardByUrl(String url) throws Exception;
    BoardRetrospective createBoard(BoardRetrospectiveDTO boardRetrospectiveDTO) throws Exception;
    BoardRetrospective editBoard(BoardRetrospective boardRetrospective,long id) throws Exception;
    BoardRetrospective deleteBoard(String url) throws Exception;
    public List<BoardColunm> findBoardByUrl1(String url)throws Exception ;
}

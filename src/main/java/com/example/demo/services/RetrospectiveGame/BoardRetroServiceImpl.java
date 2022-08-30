package com.example.demo.services.RetrospectiveGame;

import com.example.demo.dto.BoardRetrospectiveDTO;
import com.example.demo.dto.DiscussionDTO;
import com.example.demo.dto.retro.DiscussionDTO1;
import com.example.demo.model.User;
import com.example.demo.model.boardRetrospective.BoardColunm;
import com.example.demo.model.boardRetrospective.BoardRetrospective;
import com.example.demo.model.boardRetrospective.Comment;
import com.example.demo.model.boardRetrospective.Discussion;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.boardRetrospective.BoardColunmReponsitory;
import com.example.demo.repository.boardRetrospective.BoardRetroReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardRetroServiceImpl implements BoardRetroService {
    @Autowired
    private BoardRetroReponsitory boardRetroRes;
    @Autowired
    private BoardColunmReponsitory boardColunmReponsitory;
    @Autowired
    private UserRepository userRepository;

    @Override
    public BoardRetrospective findBoardByUrl(String url) throws Exception {
        BoardRetrospective boardRetrospective = boardRetroRes.findByUrl(url);
        if (boardRetrospective != null) {
            List<BoardColunm> board_Colunms = boardRetrospective.getBoard_Colunms();
            if (!CollectionUtils.isEmpty(board_Colunms)) {
                board_Colunms.forEach(item -> {
                    List<Discussion> discussionList = item.getDiscussionList();

                    if (!CollectionUtils.isEmpty(discussionList)) {
                        discussionList.forEach(discussion -> {
                            Optional<User> userOptional = userRepository.findById(discussion.getUser().getId());

                            discussion.setDisplayName(userOptional.isPresent() ? userOptional.get().getDisplayName() : "");
                        });
                    }
                    discussionList.forEach(item2 -> {
                        List<Comment> commentList = item2.getCommentList();
                        if (!CollectionUtils.isEmpty(commentList)) {
                            commentList.forEach(comment -> {
                                Optional<User> userOptional = userRepository.findById(comment.getUser().getId());

                                comment.setDisplayName(userOptional.isPresent() ? userOptional.get().getDisplayName() : "");
                            });
                        }
                    });
                });
            }
            // comment
            return boardRetrospective;
        } else {
            throw new Exception("Cannt not find id " + url);
        }
    }

    public List<BoardRetrospective> findAllBoardByUserId(Long user_id) {
        User user = userRepository.findById(user_id).get();
        List<BoardRetrospective> retrospectiveList = boardRetroRes.findByUser_id(user.getId());
        return retrospectiveList;
    }

    @Override
    public BoardRetrospective createBoard(BoardRetrospectiveDTO boardRetrospectiveDTO) throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //
        BoardRetrospective boardRetrospective = new BoardRetrospective();
        boardRetrospective.setName(boardRetrospectiveDTO.getName());
        boardRetrospective.setUrl(uuid);
        List<BoardColunm> boardColunmList = new ArrayList<>();
        //
        BoardColunm wellWent = new BoardColunm();
        wellWent.setTitle("Went Well");
        wellWent.setOrder_colum(1);
        //
        BoardColunm toImprove = new BoardColunm();
        toImprove.setTitle("To Improve");
        toImprove.setOrder_colum(2);
        //
        BoardColunm activeItem = new BoardColunm();
        activeItem.setTitle("Action Items");
        activeItem.setOrder_colum(3);
        //
        boardColunmList.add(wellWent);
        boardColunmList.add(toImprove);
        boardColunmList.add(activeItem);
//        boardColunmList.addAll(boardRetrospective.getBoard_Colunms());
        boardRetrospective.setBoard_Colunms(boardColunmList);
        //
        User user = userRepository.findById(boardRetrospectiveDTO.getUser_id()).
                orElseThrow(() -> new Exception("Not find"));
        boardRetrospective.setUser(user);
        return boardRetroRes.save(boardRetrospective);
    }

    @Override
    public BoardRetrospective editBoard(BoardRetrospective boardRetrospective, long id) throws Exception {
        BoardRetrospective findbBoardRetroById = boardRetroRes.findById(id)
                .orElseThrow(() -> new Exception("Cannt not find id " + id));
        findbBoardRetroById.setName(boardRetrospective.getName());
        boardRetroRes.save(findbBoardRetroById);
        return findbBoardRetroById;
    }

    @Override
    public BoardRetrospective deleteBoard(String url) throws Exception {
        BoardRetrospective boardRetrospective = boardRetroRes.findByUrl(url);
        boardRetroRes.delete(boardRetrospective);
        return boardRetrospective;
    }

    @Override
    public List<BoardColunm> findBoardByUrl1(String url) throws Exception {
        BoardRetrospective boardRetrospective = boardRetroRes.findByUrl(url);
        if (boardRetrospective != null) {
            List<BoardColunm> board_Colunms = boardRetrospective.getBoard_Colunms();
            if (!CollectionUtils.isEmpty(board_Colunms)) {
                board_Colunms.forEach(item -> {
                    List<Discussion> discussionList = item.getDiscussionList();

                    if (!CollectionUtils.isEmpty(discussionList)) {
                        discussionList.forEach(discussion -> {
                            Optional<User> userOptional = userRepository.findById(discussion.getUser().getId());

                            discussion.setDisplayName(userOptional.isPresent() ? userOptional.get().getDisplayName() : "");
                        });
                    }
                    discussionList.forEach(item2 -> {
                        List<Comment> commentList = item2.getCommentList();
                        if (!CollectionUtils.isEmpty(commentList)) {
                            commentList.forEach(comment -> {
                                Optional<User> userOptional = userRepository.findById(comment.getUser().getId());

                                comment.setDisplayName(userOptional.isPresent() ? userOptional.get().getDisplayName() : "");
                            });
                        }
                    });
                });
            }
        List<BoardColunm> discussions = boardRetrospective.getBoard_Colunms();
        return discussions;
    }
        else throw new Exception("sai");
}
}
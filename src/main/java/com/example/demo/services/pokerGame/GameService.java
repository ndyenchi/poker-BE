package com.example.demo.services.pokerGame;

import com.example.demo.model.pokerGame.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    public Game FindGameByUrl(String url) throws Exception;
}

package com.example.demo.services.pokerGame;

import com.example.demo.dto.pokerGame.GameUserDTO;
import com.example.demo.dto.pokerGame.TestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameUserService {
    public List<GameUserDTO> getChooseCard(String url);

    public void outGame(TestDTO testDTO);
}

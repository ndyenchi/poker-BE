package com.example.demo.services.pokerGame;

import com.example.demo.dto.pokerGame.TestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {
    public List<TestDTO> dashboard();
    public List<Long> count();
    public  List<String> month();
    public List<Long> game();
}

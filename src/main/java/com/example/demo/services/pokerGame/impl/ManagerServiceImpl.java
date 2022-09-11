package com.example.demo.services.pokerGame.impl;

import com.example.demo.dto.pokerGame.TestDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.pokerGame.GameRepository;
import com.example.demo.services.pokerGame.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired private UserRepository userRepository;
    @Autowired private GameRepository gameRepository;

    @Override
    public List<TestDTO> dashboard() {
        SimpleDateFormat thang = new SimpleDateFormat("mm");
        SimpleDateFormat nam = new SimpleDateFormat("yyyy");

        List<String> allDates = new ArrayList<>();
        List<TestDTO> list = new ArrayList<>();
        HashMap<Long, String> hashMap=new HashMap<Long, String>();
        SimpleDateFormat monthDate = new SimpleDateFormat("MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(Calendar.getInstance().getTime());
        for (int i = 0; i < 12; i++) {
            String month_name1 = monthDate.format(cal.getTime());
            allDates.add(month_name1);
            Long count = userRepository.getUserByTime(month_name1);
            if(count==0){
                hashMap.put( 0L ,month_name1);
            }else{
                hashMap.put(count, month_name1);
            }
            cal.add(Calendar.MONTH, -1);
            hashMap.forEach((key, value) -> {
                TestDTO test= new TestDTO();
                test.setIdUser(key);
                test.setUrl(value);
                list.add(test);
            });
            hashMap.clear();
        }
    return list;
    }

    @Override
    public List<Long> count() {
        List<TestDTO> list=dashboard();
        List<Long> count=new ArrayList<>();
        for(int i=list.size()-1;i>=0 ;i--){
            count.add(list.get(i).getIdUser());
        }
        return count;
    }

    @Override
    public List<String> month() {
        List<TestDTO> list=dashboard();
        List<String> month=new ArrayList<>();
        for(int i=list.size()-1;i>=0 ;i--){
            month.add(list.get(i).getUrl());
        }
        return month;
    }

    @Override
    public List<Long> game() {
        List<Long> game=new ArrayList<>();
        List<String> month=month();
        for(String i: month){
            game.add(gameRepository.getTimeCreateGame(i));
        }
        return game;
    }
}

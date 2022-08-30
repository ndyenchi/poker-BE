package com.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dao.nduytan on 04/06/2022
 */
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topics")
    String getAllTopic() throws JsonProcessingException {
        List<Topic> result = topicRepository.findAll();
        Map response = new HashMap();
        response.put("result", result);
        response.put("statusCode", "SUCCESS");
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(response);
    }
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }
}

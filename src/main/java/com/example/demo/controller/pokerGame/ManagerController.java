package com.example.demo.controller.pokerGame;

import antlr.Utils;
import com.example.demo.dto.pokerGame.TestDTO;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.model.User;
import com.example.demo.repository.pokerGame.UsersRepository;
import com.example.demo.services.pokerGame.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired private UsersRepository usersRepository;
    @Autowired private ManagerService managerService;
    @GetMapping("/users")
    public List<User> getAllUser(){
        return usersRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public User getAllUser(@PathVariable long id){
        return usersRepository.findById(id).get();

    }
    @PutMapping("/users")
    public ResponseEntity saveUser(@RequestBody User user) {
        try {
            User u = usersRepository.findById(user.getId()).get();
            u.setId(user.getId());
            u.setName(user.getName());
            u.setDob(user.getDob());
            u.setAddress(user.getAddress());
            u.setDisplayName(user.getDisplayName());
            u.setPhone(user.getPhone());
            return ResponseHelper.GenerateResponse(true, "", "", usersRepository.save(u), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHelper.GenerateResponse(false, "update user fail", HttpStatus.OK);
        }
    }
    @GetMapping()
    public List<TestDTO> dashboard(){
        return managerService.dashboard();
    }

    @GetMapping("/count")
    public List<Long> count(){
        return managerService.count();
    }

    @GetMapping("/month")
    public List<String> month(){
        return managerService.month();
    }
    @GetMapping("/game")
    public List<Long> game(){
        return managerService.game();
    }
}

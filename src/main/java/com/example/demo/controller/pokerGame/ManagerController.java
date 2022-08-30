package com.example.demo.controller.pokerGame;

import antlr.Utils;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.model.User;
import com.example.demo.repository.pokerGame.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired private UsersRepository usersRepository;
    @GetMapping("/users")
    public ResponseEntity getAllUser(){
        return ResponseHelper.GenerateResponse(true, "", "",usersRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity getAllUser(@PathVariable long id)throws Exception{
        Optional user = usersRepository.findById(id);
        if(user.isPresent()){
            return ResponseHelper.GenerateResponse(true, "", "",user.get(), HttpStatus.OK);
        }
        else{
            return ResponseHelper.GenerateResponse(false,new Exception().getMessage(),HttpStatus.OK);
        }
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
}

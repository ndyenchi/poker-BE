package com.example.demo.controller.pokerGame;

import com.example.demo.dto.EmailResponse;
import com.example.demo.dto.pokerGame.ExportCSV;
import com.example.demo.helper.ResponseHelper;
import com.example.demo.model.pokerGame.Game;
import com.example.demo.services.ForgotPassword.ForgotPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private ForgotPassService forgotPassService;
    @PostMapping("/sendMail")
    public ResponseEntity sendMail(@RequestBody EmailResponse email){
        try{
             forgotPassService.sendMailAttach(email);
            return ResponseHelper.GenerateResponse(true,"success", HttpStatus.OK);
        }catch (Exception e){
            return ResponseHelper.GenerateResponse(false, e.getMessage(), HttpStatus.OK);
        }
    }
//    @GetMapping("/test")
//    public ExportCSV test(){
//        ExportCSV a =new ExportCSV("1",1L,1L, 1L);
//        return a;
//    }
}

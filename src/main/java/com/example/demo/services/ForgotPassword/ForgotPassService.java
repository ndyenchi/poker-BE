package com.example.demo.services.ForgotPassword;

import com.example.demo.dto.EmailResponse;
import com.example.demo.dto.ResetPassWordReponse;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface ForgotPassService {
    public EmailResponse sendMailAttach(EmailResponse email) throws MessagingException;
    String forgotPassword(String email);
    public String resetPassword(ResetPassWordReponse resetPassWordReponse);
}

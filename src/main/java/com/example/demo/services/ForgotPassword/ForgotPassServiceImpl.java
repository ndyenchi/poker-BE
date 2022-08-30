package com.example.demo.services.ForgotPassword;

import com.example.demo.dto.EmailResponse;
import com.example.demo.dto.ResetPassWordReponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class ForgotPassServiceImpl implements  ForgotPassService {
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JavaMailSender javaMailSender;
    public EmailResponse sendMailAttach(EmailResponse email) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
        String response = forgotPassword(email.getEmailTo());
        String link = "http://localhost:4200/reset-password/" + response;
        String content = "<p>Hello,</p>"
                + "<p>Follow this link to reset your Retrospective game online password for your </p>"
                + email.getEmailTo() +"account"
                + "<p><a href=\"" + link + "\">Reset password form’s link</a></p>"
                + "<p>If you didn’t ask to reset your password, you can ignore this email."
                + "<br>"
                + "Thanks,</p>"
                + "<p>TPS Software</p>" ;
        helper.setTo(email.getEmailTo());
        helper.setSubject("Tien minh");
        helper.setText(content, true);
//        ClassPathResource pathResource=new ClassPathResource("download.jpeg");
//        helper.addAttachment("download.jpeg",pathResource);
        javaMailSender.send(mimeMailMessage);
        return email;
    }

    public String forgotPassword(String email) {
        User userOptional = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found with username: " + email));
        userOptional.setToken(generateToken());
        userOptional.setTokenCreationDate(LocalDateTime.now());

        userOptional = userRepository.save(userOptional);

        return userOptional.getToken();
    }
    public String resetPassword(ResetPassWordReponse resetPassWordReponse) {

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByToken(resetPassWordReponse.getToken()));
        if (!userOptional.isPresent()) {
            return "Invalid token.";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";
        }
        User user = userOptional.get();
        user.setPassword(encoder.encode(resetPassWordReponse.getPassword()));
        user.setToken(null);
        user.setTokenCreationDate(null);
        userRepository.save(user);
        return "Your password successfully updated.";
    }
    private String generateToken() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;
    }
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}


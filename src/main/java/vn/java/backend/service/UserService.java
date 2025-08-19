package vn.java.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.java.backend.model.entity.User;
import vn.java.backend.model.request.LoginRequest;
import vn.java.backend.model.request.RegisterRequest;
import vn.java.backend.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists !");
        }
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists !");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);

        String code = UUID.randomUUID().toString();
        user.setVerificationCode(code);

        userRepository.save(user);

        mailService.sendVerificationEmail(user.getEmail(), code);
    }

    public boolean verifyUser(String code ){

        Optional<User> optionalUser = userRepository.findByVerificationCode(code);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setVerified(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new RuntimeException("Invalid username or password!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password!");
        }

        if (!user.isVerified()) {
            throw new RuntimeException("Please verify your email first!");
        }

        return user;
    }




}

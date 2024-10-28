package com.bugrak.eventTime.service;

import com.bugrak.eventTime.md5.EmailValidator;
import com.bugrak.eventTime.md5.HashUtil;
import com.bugrak.eventTime.model.User;
import com.bugrak.eventTime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser (User user)
    {
        if (!EmailValidator.isValidEmail(user.getEmail())){
            throw new IllegalArgumentException("Geçersiz mail hesabı");
        }
        String hashedPassword = HashUtil.md5(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public boolean login(String email,String password){
        User user = userRepository.findByEmail(email);
        if (user != null){
            String hashedPassword = HashUtil.md5(password);
            return hashedPassword.equals(user.getPassword());
        }

        return false;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Integer userId){
        return userRepository.findById(userId);
    }
}

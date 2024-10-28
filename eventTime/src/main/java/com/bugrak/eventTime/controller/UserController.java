package com.bugrak.eventTime.controller;

import com.bugrak.eventTime.dto.UserDto;
import com.bugrak.eventTime.model.User;
import com.bugrak.eventTime.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        try{
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto loginDto, HttpSession session){
        if (userService.login(loginDto.getEmail(), loginDto.getPassword())){
            User user = userService.findByEmail(loginDto.getEmail());

            session.setAttribute("id",user.getId());
            session.setAttribute("username", user.getName());
            session.setAttribute("email", user.getEmail());

            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @GetMapping("/logout")
    public  ResponseEntity<String> logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return ResponseEntity.ok("Çıkış Yapıldı");
    }
}

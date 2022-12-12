package com.example.jlrform.controller;

import com.example.jlrform.dto.UserDto;
import com.example.jlrform.entity.User;
import com.example.jlrform.service.UserService;
import java.util.List;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tshi1
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public User saveUser(@RequestParam String eName
            ,@RequestParam String cName
            ,@RequestParam String cdsid
            ,@RequestParam Boolean involved
            ,@RequestParam int score) {

        return userService.saveUser(cdsid,eName,cName,involved,score);
    }

    @GetMapping("/user/rank")
    public List<UserDto> getRank() {

        return userService.getRank();
    }
}

package com.example.jlrform.controller;

import com.example.jlrform.entity.User;
import com.example.jlrform.service.UserService;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Response<User> setCountDownPageNumber() {
        Response<User> response = new Response<>();

        return new Response<>(userService.getAllUsers());
    }
}
